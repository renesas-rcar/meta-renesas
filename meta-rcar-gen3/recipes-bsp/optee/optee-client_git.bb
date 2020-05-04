DESCRIPTION = "OP-TEE Client"
LICENSE = "BSD-2-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=69663ab153298557a59c67a60a743e5b"
PR = "r0"
PV = "3.1.0+renesas+git${SRCPV}"
BRANCH = "master"
SRC_URI = "git://github.com/OP-TEE/optee_client.git;branch=${BRANCH}"
SRCREV = "3f16662284a69fdec97b1712064be94d1fed7ae7"

SRC_URI += " \
    file://optee.service \
    file://0001-tee-supplicant-use-MMC_IOC_MULTI_CMD-for-RPMB-access.patch \
    file://0001-Fix-for-teec_trace.c-snprintf-Werror-format-truncati.patch \
"

inherit python3native systemd
SYSTEMD_SERVICE_${PN} = "optee.service"

COMPATIBLE_MACHINE = "salvator-x|h3ulcb|m3ulcb|m3nulcb|ebisu"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

# Recipe which fail to compile when enabling _FORTIFY_SOURCE=2 option
SECURITY_CFLAGS_pn-optee-client = ""

EXTRA_OEMAKE = "RPMB_EMU=0"

do_install () {
    # Create destination directories
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}

    # Install library
    install -m 0755 ${S}/out/export/lib/libteec.so.1.0 ${D}/${libdir}

    # Create symbolic link
    cd ${D}/${libdir}
    ln -sf libteec.so.1.0 libteec.so.1
    ln -sf libteec.so.1 libteec.so

    # Install header files
    install -m 0644 ${S}/out/export/include/* ${D}/${includedir}

    # Install systemd service configure file for OP-TEE client
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/optee.service ${D}/${systemd_system_unitdir}
    fi
}

# install the tee-supplicant for 64 bit only.
do_install_append_aarch64 () {
    # Create destination directory
    install -d ${D}/${bindir}

    # Install binary to bindir
    install -m 0755 ${S}/out/export/bin/tee-supplicant ${D}/${bindir}
}

RPROVIDES_${PN} += "optee-client"

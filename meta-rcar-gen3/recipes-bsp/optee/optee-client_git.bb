DESCRIPTION = "OP-TEE Client"
LICENSE = "BSD-2-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=69663ab153298557a59c67a60a743e5b"
PR = "r0"
PV = "2.6.0+renesas+git${SRCPV}"
BRANCH = "master"
SRC_URI = "git://github.com/OP-TEE/optee_client.git;branch=${BRANCH}"
SRCREV = "73b4e490a8ed0b4a7714818e80998b9d8a7da958"

SRC_URI += " \
    file://0001-Add-LDFLAGS-option.patch \
    file://optee.service \
"

inherit systemd
SYSTEMD_SERVICE_${PN} = "optee.service"

COMPATIBLE_MACHINE = "salvator-x|h3ulcb|m3ulcb|ebisu"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

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

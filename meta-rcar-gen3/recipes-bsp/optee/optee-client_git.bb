DESCRIPTION = "OP-TEE Client"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=69663ab153298557a59c67a60a743e5b"

inherit python3native systemd pkgconfig

COMPATIBLE_MACHINE = "salvator-x|ulcb|ebisu|draak"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "util-linux"

PV = "3.22.0+renesas+git${SRCPV}"
PR = "r0"

BRANCH = "master"
SRC_URI = "git://github.com/OP-TEE/optee_client.git;branch=${BRANCH};protocol=https"
SRCREV = "8533e0e6329840ee96cf81b6453f257204227e6c"

SRC_URI += " \
    file://optee.service \
    file://0001-tee-supplicant-Delete-the-sleep-time-when-writing-da.patch \
"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE:${PN} = "optee.service"

# Recipe which fail to compile when enabling _FORTIFY_SOURCE=2 option
SECURITY_CFLAGS:pn-optee-client = ""

EXTRA_OEMAKE = "RPMB_EMU=0 PKG_CONFIG=pkg-config"

do_install () {
    # Create destination directories
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}

    # Install library
    install -m 0755 ${S}/out/export/usr/lib/libteec.so.1.0.0 ${D}/${libdir}

    # Create symbolic link
    cd ${D}/${libdir}
    ln -sf libteec.so.1.0.0 libteec.so.1.0
    ln -sf libteec.so.1.0 libteec.so.1
    ln -sf libteec.so.1 libteec.so

    # Install header files
    install -m 0644 ${S}/out/export/usr/include/* ${D}/${includedir}

    # Install systemd service configure file for OP-TEE client
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/optee.service ${D}/${systemd_system_unitdir}
    fi
}

# install the tee-supplicant for 64 bit only.
do_install:append:aarch64 () {
    # Create destination directory
    install -d ${D}/${bindir}

    # Install binary to bindir
    install -m 0755 ${S}/out/export/usr/sbin/tee-supplicant ${D}/${bindir}
}

RPROVIDES:${PN} += "optee-client"

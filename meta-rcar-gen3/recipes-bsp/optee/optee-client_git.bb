DESCRIPTION = "OP-TEE Client"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f0fb2f357d31d6a98213b19f57abf927"
PN = "optee-client"
PR = "r0"
BRANCH = "master"
SRC_URI = "git://github.com/OP-TEE/optee_client.git;branch=${BRANCH}"
SRCREV = "6b08c092f79e1aade3a5ee1b78c4ddb345f8a1f0"

SRC_URI += " \
    file://0001-add-optee_client-R-Car-support.patch \
    file://optee.service \
"

inherit systemd
SYSTEMD_SERVICE_${PN} = "optee.service"

PV = "1.0.0+renesas+git${SRCPV}"
COMPATIBLE_MACHINE = "salvator-x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile() {
    cd ${S}/
    oe_runmake
}

do_install () {
    # Create destination folders
    install -d ${D}${libdir}
    install -d ${D}${includedir}

    # Copy library
    install -m 0755 ${S}/out/export/lib/libteec.so.1.0 ${D}${libdir}

    # Create symbolic link
    cd ${D}${libdir}
    ln -sf libteec.so.1.0 libteec.so.1
    ln -sf libteec.so.1 libteec.so

    install -m 0644 ${S}/out/export/include/* ${D}${includedir}

    # Install systemd service configure file for OP-TEE client
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/optee.service ${D}${systemd_unitdir}/system
    fi
}

# install the tee-supplicant for 64 bit only.
do_install_append_aarch64 () {
    # Create destination folder
    install -d ${D}${bindir}

    # Copy binary to bindir
    install -m 0755 ${S}/out/export/bin/tee-supplicant ${D}${bindir}
}

RPROVIDES_${PN} += "optee-client"

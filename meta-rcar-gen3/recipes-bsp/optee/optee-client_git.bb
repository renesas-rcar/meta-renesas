DESCRIPTION = "OP-TEE Client"
LICENSE = "BSD-2-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=69663ab153298557a59c67a60a743e5b"
PN = "optee-client"
PR = "r0"
BRANCH = "master"
SRC_URI = "git://github.com/OP-TEE/optee_client.git;branch=${BRANCH}"
SRCREV = "db9c64d45818d146200297eaaedbd421a8b59e3a"

SRC_URI += " \
    file://0001-add-optee_client-R-Car-support.patch \
    file://optee.service \
"

inherit systemd
SYSTEMD_SERVICE_${PN} = "optee.service"

PV = "1.0.0+renesas+git${SRCPV}"
COMPATIBLE_MACHINE = "(salvator-x|h3ulcb|m3ulcb)"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

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

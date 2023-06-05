DESCRIPTION = "ADSP Framework for Linux"
LICENSE = "CLOSED"

inherit features_check

REQUIRED_DISTRO_FEATURES = "adsp"
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

SRC_URI = "file://RTM8RC0000ZNA3SS00JFL3E.tar.gz"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/RTM8RC0000ZNA3SS00JFL3E"

do_install() {
    # create the firmware dir
    install -d ${D}${nonarch_base_libdir}/firmware

    # install the firmware bin
    install -m 0644 ${S}${nonarch_base_libdir}/firmware/xf-rcar.fw ${D}${nonarch_base_libdir}/firmware
}

PACKAGES = " \
    ${PN} \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/firmware \
"

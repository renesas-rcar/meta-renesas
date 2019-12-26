DESCRIPTION = "ADSP Framework for Linux"
LICENSE = "CLOSED"

inherit features_check

REQUIRED_DISTRO_FEATURES = "adsp"
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

SRC_URI = "file://RCG3AHFWN0201ZDP.tar.gz"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/RCG3AHFWN0201ZDP"

do_install() {
    # create the firmware dir
    install -d ${D}/lib/firmware

    # install the firmware bin
    install -m 0644 ${S}/lib/firmware/xf-rcar.fw ${D}/lib/firmware
}

PACKAGES = " \
    ${PN} \
"

FILES_${PN} = " \
    /lib/firmware \
"

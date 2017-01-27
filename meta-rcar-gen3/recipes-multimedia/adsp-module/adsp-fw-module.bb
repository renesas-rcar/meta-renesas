DESCRIPTION = "ADSP Framework for Linux"
LICENSE = "CLOSED"

require include/adsp-control.inc

SRC_URI = "${@base_conditional('USE_ADSP', '1', 'file://RCG3AHFWN0101ZDP.tar.gz', '', d )}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/RCG3AHFWN0101ZDP"

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

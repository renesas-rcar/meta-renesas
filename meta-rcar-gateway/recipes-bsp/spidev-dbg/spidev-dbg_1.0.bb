SUMMARY = "SPI device debug utility"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=26cdfe4d6a85afebc7ccd5623f195fa2"

S = "${WORKDIR}/spidev-dbg"

SRC_URI = " \
    file://spidev-dbg.tar.gz \
"

do_compile() {
    cd ${S}
    make all || die
}

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/spidev-dbg ${D}${bindir}
}

FILES_${PN} = "${bindir}/spidev-dbg"

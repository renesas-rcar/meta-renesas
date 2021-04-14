SUMMARY = "CPURTT drive Module"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://${S}/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://${S}/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

inherit module

PR = "r0"
PV = "0.1"

RENESAS_CPURTTDRV_URL ?= "git://github.com/renesas-rcar/cpurttdrv.git"

SRC_URI = "${RENESAS_CPURTTDRV_URL};nobranch=1"
SRCREV = "e5854521959c8483d9f9a5c8a8277d75a2b7a00d"

S = "${WORKDIR}/git"

do_install_append () {
    install -d ${D}${includedir}
    install -m 644 ${S}/cpurtt_common.h ${D}${includedir}
}

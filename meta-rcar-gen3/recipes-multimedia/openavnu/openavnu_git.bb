DESCRIPTION = "OpenAvnu - an AVnu sponsored repository for Time Sensitive Network (TSN and AVB) technology"
HOMEPAGE = "https://github.com/AVnu/OpenAvnu"
LICENSE = "BSD-3-Clause & MIT & GPL-2.0-only & LGPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://daemons/LICENSE;md5=81ccd62d4bc28bafc5e1a2576536b927 \
    file://daemons/shaper/LICENSE;md5=53e60f7a40864e4c075831415f2ecc59 \
    file://lib/avtp_pipeline/LICENSE;md5=485c2c585be88b59c3ee70a016f27959 \
    file://examples/LICENSE;md5=81ccd62d4bc28bafc5e1a2576536b927 \
    file://examples/gstreamer-avb-plugins/COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
    file://examples/gstreamer-avb-plugins/COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc \
"

DEPENDS = "libpcap"
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"

PV = "1.1+git${SRCPV}"

SRCREV = "ff076e83234d2207f33447b9bd6d1646d9245566"
SRC_URI = "git://github.com/AVnu/OpenAvnu.git;branch=master;protocol=https"


S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CC=${CC}' 'CXX=${CXX}'"

do_configure() {
    oe_runmake daemons_all_clean
}

do_compile() {
    oe_runmake daemons_all
}

do_install() {
    install -d ${D}/${bindir}
    install -m 755 daemons/maap/linux/build/maap_daemon ${D}/${bindir}
    install -m 755 daemons/mrpd/mrpd daemons/mrpd/mrpctl ${D}/${bindir}
}

PACKAGES =+ " \
    ${PN}-mrpd \
    ${PN}-maap \
"

ALLOW_EMPTY:${PN} = "1"


FILES:${PN}-mrpd = " \
    ${bindir}/mrpd \
    ${bindir}/mrpctl \
"

FILES:${PN}-maap = " \
    ${bindir}/maap_daemon \
"

RDEPENDS:${PN} = " \
    ${PN}-mrpd \
    ${PN}-maap \
"

RDEPENDS:${PN}-maap = " \
    libpcap \
"

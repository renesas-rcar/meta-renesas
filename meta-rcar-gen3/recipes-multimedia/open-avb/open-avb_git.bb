DESCRIPTION = "Open AVB - an AVnu sponsored repository for Audio/Video Bridging technology"
HOMEPAGE = "https://github.com/AVnu/Open-AVB"
LICENSE = "BSD & MIT & GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = " \
    file://daemons/LICENSE;md5=81ccd62d4bc28bafc5e1a2576536b927 \
    file://kmod/igb/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://kmod/igb/LICENSE;md5=e2c0cd0820d168b0b26e19f13df4dc56 \
    file://lib/igb/LICENSE;md5=9bc783ca40be823a8b237df5eed80fc4 \
    file://lib/avtp_pipeline/LICENSE;md5=8f7b370a91d698ed80d2d20e8e01fbb6 \
    file://examples/LICENSE;md5=81ccd62d4bc28bafc5e1a2576536b927 \
    file://examples/gstreamer-avb-plugins/COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
    file://examples/gstreamer-avb-plugins/COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc \
"

DEPENDS = "libpcap"

PV = "1.1+git${SRCPV}"

SRCREV = "bfa7f9d0c5c8ffe1c0248c6415b3f94e8b7f10c5"
SRC_URI = "git://github.com/AVnu/Open-AVB.git;branch=open-avb-next"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CC=${CC}' 'CXX=${CXX}'"

do_compile() {
    oe_runmake daemons_all
}

do_install() {
    install -d ${D}/${bindir}
    install -m 755 daemons/maap/linux/maap_daemon ${D}/${bindir}
    install -m 755 daemons/mrpd/mrpd daemons/mrpd/mrpctl ${D}/${bindir}
    install -m 755 daemons/gptp/linux/build/obj/daemon_cl ${D}/${bindir}
}

PACKAGES =+ " \
    ${PN}-gptp \
    ${PN}-mrpd \
    ${PN}-maap \
"

ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-gptp = " \
    ${bindir}/daemon_cl \
"

FILES_${PN}-mrpd = " \
    ${bindir}/mrpd \
    ${bindir}/mrpctl \
"

FILES_${PN}-maap = " \
    ${bindir}/maap_daemon \
"

RDEPENDS_${PN} = " \
    ${PN}-gptp \
    ${PN}-mrpd \
    ${PN}-maap \
"

RDEPENDS_${PN}-maap = " \
    libpcap \
"

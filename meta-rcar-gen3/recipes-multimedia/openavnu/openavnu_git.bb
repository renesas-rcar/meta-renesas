DESCRIPTION = "OpenAvnu - an AVnu sponsored repository for Time Sensitive Network (TSN and AVB) technology"
HOMEPAGE = "https://github.com/AVnu/OpenAvnu"
LICENSE = "BSD & MIT & GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = " \
    file://daemons/LICENSE;md5=81ccd62d4bc28bafc5e1a2576536b927 \
    file://daemons/shaper/LICENSE;md5=53e60f7a40864e4c075831415f2ecc59 \
    file://kmod/igb/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://kmod/igb/LICENSE;md5=e2c0cd0820d168b0b26e19f13df4dc56 \
    file://lib/igb/LICENSE;md5=9bc783ca40be823a8b237df5eed80fc4 \
    file://lib/avtp_pipeline/LICENSE;md5=485c2c585be88b59c3ee70a016f27959 \
    file://examples/LICENSE;md5=81ccd62d4bc28bafc5e1a2576536b927 \
    file://examples/gstreamer-avb-plugins/COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605 \
    file://examples/gstreamer-avb-plugins/COPYING.MIT;md5=bba6cdb9c2b03c849ed4975ed9ed90dc \
"

DEPENDS = "libpcap"
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

PV = "1.1+git${SRCPV}"

SRCREV = "5e43fa5ae27fbd2b89c320670a32143ce93e0e16"
SRC_URI = "git://github.com/AVnu/OpenAvnu.git;branch=master"

# Fix QA issue of ldflags
SRC_URI_append = " file://0001-Fix-QA-issue-when-build-with-Yocto.patch"

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

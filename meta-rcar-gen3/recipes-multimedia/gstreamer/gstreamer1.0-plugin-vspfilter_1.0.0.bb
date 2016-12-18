SUMMARY = "GStreamer VSP filter plugin"
SECTION = "multimedia"
LICENSE = "GPLv2+"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base pkgconfig"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"
inherit autotools

PN = "gstreamer1.0-plugin-vspfilter"

EXTRA_AUTORECONF_append = " -I ${STAGING_DATADIR}/aclocal"

VSPFILTER_CONF_r8a7795 = "gstvspfilter-${MACHINE}_r8a7795.conf"
VSPFILTER_CONF_r8a7796 = "gstvspfilter-${MACHINE}_r8a7796.conf"

SRC_URI = " \
    git://github.com/CogentEmbedded/gst-plugin-vspfilter.git;nobranch=1 \
    file://${VSPFILTER_CONF} \
"

SRCREV = "e66072332dcb771b9a4687d713076465aee34e28"

S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${libdir}/gstreamer-1.0/libgstvspfilter.so \
    ${sysconfdir}/gstvspfilter.conf"
FILES_${PN}-dev = "${libdir}/gstreamer-1.0/libgstvspfilter.la"
FILES_${PN}-staticdev = "${libdir}/gstreamer-1.0/libgstvspfilter.a"
FILES_${PN}-dbg = " \
    ${libdir}/gstreamer-1.0/.debug \
    ${prefix}/src"

do_install_append() {
    mkdir ${D}/etc/
    install -m 644 ${WORKDIR}/${VSPFILTER_CONF} ${D}/etc/gstvspfilter.conf
}

RDEPENDS_${PN} = "kernel-module-vsp2driver"

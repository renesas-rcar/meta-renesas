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
    git://github.com/renesas-rcar/gst-plugin-vspfilter.git;branch=RCAR-GEN3/1.0.0 \
    file://${VSPFILTER_CONF} \
"

SRCREV = "a1fa0a51e7d93ddd04b66521f8c5c4a486fdcc17"

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
    install -Dm 644 ${WORKDIR}/${VSPFILTER_CONF} ${D}/etc/gstvspfilter.conf
}

RDEPENDS_${PN} = "kernel-module-vsp2driver"

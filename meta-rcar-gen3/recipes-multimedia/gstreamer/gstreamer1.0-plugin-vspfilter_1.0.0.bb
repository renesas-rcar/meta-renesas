SUMMARY = "GStreamer VSP filter plugin"
SECTION = "multimedia"
LICENSE = "GPLv2+"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base pkgconfig"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"
inherit autotools

PN = "gstreamer1.0-plugin-vspfilter"

EXTRA_AUTORECONF_append = " -I ${STAGING_DATADIR}/aclocal"

SRC_URI = " \
    git://github.com/renesas-rcar/gst-plugin-vspfilter.git;protocol=git;branch=RCAR-GEN3/1.0.0 \
    file://gstvspfilter-salvator-x.conf \
"
SRCREV = "fa4b389dfc3f78d39820f4248e7e37aeb333b0c5"

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
    install -m 644 ${WORKDIR}/gstvspfilter-${MACHINE}.conf ${D}/etc/gstvspfilter.conf
}

RDEPENDS_${PN} = "kernel-module-vsp2driver"

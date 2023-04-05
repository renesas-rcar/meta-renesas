SUMMARY = "GStreamer VSP filter plugin"
SECTION = "multimedia"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

RENESAS_VSPFILTER_URL ?= "gitsm://github.com/renesas-rcar/gst-plugin-vspfilter.git;branch=RCAR-GEN3e/1.0.4;protocol=https;lfs=0"

SRC_URI = " \
    ${RENESAS_VSPFILTER_URL} \
"

SRCREV = "5984661366b7b0bdc79fb34b6dfbaaf9dba24623"

S = "${WORKDIR}/git"


inherit meson pkgconfig

DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base pkgconfig"

FILES:${PN} = " \
    ${libdir}/gstreamer-1.0/libgstvspfilter.so \
"

FILES:${PN}-dev = "${libdir}/gstreamer-1.0/libgstvspfilter.la"

FILES:${PN}-staticdev = "${libdir}/gstreamer-1.0/libgstvspfilter.a"

FILES:${PN}-dbg = " \
    ${libdir}/gstreamer-1.0/.debug \
    ${prefix}/src \
"

RDEPENDS:${PN} = "kernel-module-vsp2driver gstreamer1.0-plugin-vspfilter-config"

SUMMARY = "GStreamer VSP filter plugin"
SECTION = "multimedia"
LICENSE = "GPLv2+"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base pkgconfig"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"
inherit autotools pkgconfig

PN = "gstreamer1.0-plugin-vspfilter"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

EXTRA_AUTORECONF_append = " -I ${STAGING_DATADIR}/aclocal"

VSPFILTER_CONF_r8a7795 = "gstvspfilter-${MACHINE}_r8a7795.conf"
VSPFILTER_CONF_r8a7796 = "gstvspfilter-${MACHINE}_r8a7796.conf"
VSPFILTER_CONF_r8a77965 = "gstvspfilter-${MACHINE}_r8a77965.conf"
VSPFILTER_CONF_r8a77990 = "gstvspfilter-${MACHINE}_r8a77990.conf"

SRC_URI = " \
    gitsm://github.com/renesas-rcar/gst-plugin-vspfilter.git;branch=RCAR-GEN3/1.0.1 \
    file://${VSPFILTER_CONF} \
"

SRCREV = "e94cf40ab5515abd716b21f77caa48db0d077927"

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

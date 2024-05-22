require include/rcar-gen3-path-common.inc
require gstreamer.inc

SUMMARY = "OpenMAX IL plugins for GStreamer"
DESCRIPTION = "Wraps available OpenMAX IL components and makes them available as standard GStreamer elements."
HOMEPAGE = "https://github.com/renesas-rcar/gstreamer/"
SECTION = "multimedia"

LICENSE = "LGPL-2.1-or-later"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=4b2e62aace379166f9181a8571a14882 \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

SRC_URI = " \
    ${RENESAS_GST_URL} \
    file://gstomx.conf \
"

S = "${WORKDIR}/git/subprojects/gst-omx"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad virtual/libomxil"
DEPENDS:append = " omx-user-module mmngrbuf-user-module"

inherit meson pkgconfig upstream-version-is-even

GSTREAMER_1_0_OMX_TARGET = "rcar"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libomxr_core.so"

EXTRA_OEMESON:append = " -Dtarget=${GSTREAMER_1_0_OMX_TARGET}"
EXTRA_OEMESON:append = " -Dheader_path=${STAGING_DIR_TARGET}/usr/local/include"

python __anonymous () {
    omx_target = d.getVar("GSTREAMER_1_0_OMX_TARGET")
    if omx_target in ['generic', 'bellagio']:
        # Bellagio headers are incomplete (they are missing the OMX_VERSION_MAJOR,#
        # OMX_VERSION_MINOR, OMX_VERSION_REVISION, and OMX_VERSION_STEP macros);
        # appending a directory path to gst-omx' internal OpenMAX IL headers fixes this
        d.appendVar("CFLAGS", " -I${S}/omx/openmax")
    elif omx_target == "rpi":
        # Dedicated Raspberry Pi OpenMAX IL support makes this package machine specific
        d.setVar("PACKAGE_ARCH", d.getVar("MACHINE_ARCH"))
}

do_configure:prepend() {
    cd ${S}
    install -m 0644 ${WORKDIR}/gstomx.conf ${S}/config/rcar/
    sed -i 's,@RENESAS_DATADIR@,${RENESAS_DATADIR},g' ${S}/config/rcar/gstomx.conf
    cd ${B}
}

set_omx_core_name() {
    sed -i -e "s;^core-name=.*;core-name=${GSTREAMER_1_0_OMX_CORE_NAME};" "${D}${sysconfdir}/xdg/gstomx.conf"
}
do_install[postfuncs] += " set_omx_core_name "

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES:${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"

RDEPENDS:${PN}:append = " omx-user-module"
RDEPENDS:${PN}:remove = "libomxil"


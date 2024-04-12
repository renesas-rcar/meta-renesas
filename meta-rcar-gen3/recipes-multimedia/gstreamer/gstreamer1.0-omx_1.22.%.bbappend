FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

require include/rcar-gen3-path-common.inc
require gstreamer.inc
RENESAS_OMX_URL ?= "gitsm://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN3e/1.16.3;protocol=https"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"
SRC_URI:append = " \
    ${RENESAS_OMX_URL} \
    file://gstomx.conf \
"

DEPENDS += "omx-user-module mmngrbuf-user-module"

SRCREV = "37296f66e3392d8dcdcdae14b89b05dd7507dc39"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=4b2e62aace379166f9181a8571a14882 \
"

S = "${WORKDIR}/git"

GSTREAMER_1_0_OMX_TARGET = "rcar"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libomxr_core.so"
EXTRA_OEMESON:append = " -Dheader_path=${STAGING_DIR_TARGET}/usr/local/include"

do_configure:prepend() {
    cd ${S}
    install -m 0644 ${WORKDIR}/gstomx.conf ${S}/config/rcar/
    sed -i 's,@RENESAS_DATADIR@,${RENESAS_DATADIR},g' ${S}/config/rcar/gstomx.conf
    cd ${B}
}

RDEPENDS:${PN}:append = " omx-user-module"
RDEPENDS:${PN}:remove = "libomxil"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

RENESAS_OMX_URL ?= "gitsm://github.com/renesas-rcar/gstreamer.git;branch=RCAR-GEN3e/1.20.1;protocol=https"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"
SRC_URI:append = " \
    ${RENESAS_OMX_URL} \
    file://gstomx.conf \
"

require include/rcar-gen3-path-common.inc

DEPENDS += "omx-user-module mmngrbuf-user-module"

SRCREV = "c8e94177fecb95bc0dcd9d86e8f67cdbc9d913eb"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=4b2e62aace379166f9181a8571a14882 \
"

S = "${WORKDIR}/git/subprojects/gst-omx"

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

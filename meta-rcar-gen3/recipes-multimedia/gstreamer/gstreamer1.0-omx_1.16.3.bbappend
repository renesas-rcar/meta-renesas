FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

RENESAS_OMX_URL ?= "gitsm://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN3/1.16.3"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"
SRC_URI_append = " \
    ${RENESAS_OMX_URL} \
    file://gstomx.conf \
"

require include/rcar-gen3-path-common.inc

DEPENDS += "omx-user-module mmngrbuf-user-module"

SRCREV = "d9748c48c75e8c108af0300d9f452282d266ebd5"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=e2c6664eda77dc22095adbed9cb6c6e4 \
"

S = "${WORKDIR}/git"

GSTREAMER_1_0_OMX_TARGET = "rcar"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libomxr_core.so"
EXTRA_OEMESON_append = " -Dheader_path=${STAGING_DIR_TARGET}/usr/local/include"

do_configure_prepend() {
    cd ${S}
    install -m 0644 ${WORKDIR}/gstomx.conf ${S}/config/rcar/
    sed -i 's,@RENESAS_DATADIR@,${RENESAS_DATADIR},g' ${S}/config/rcar/gstomx.conf
    cd ${B}
}

RDEPENDS_${PN}_append = " omx-user-module"
RDEPENDS_${PN}_remove = "libomxil"

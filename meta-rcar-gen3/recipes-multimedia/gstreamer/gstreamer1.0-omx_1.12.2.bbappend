FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_remove = "http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"
SRC_URI_append = " \
    git://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN3/1.12.2 \
    file://gstomx.conf \
"

DEPENDS += "omx-user-module mmngrbuf-user-module"

SRCREV = "6813dedbc9a61ea8a80e54fc373fa07d37114d4a"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=41f577b291a84518889deaaaf2bcbc95 \
"

S = "${WORKDIR}/git"

GSTREAMER_1_0_OMX_TARGET = "rcar"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libomxr_core.so"
EXTRA_OECONF_append = " --enable-experimental"

do_configure_prepend() {
    export http_proxy=${http_proxy}
    export https_proxy=${https_proxy}
    export HTTP_PROXY=${HTTP_PROXY}
    export HTTPS_PROXY=${HTTPS_PROXY}
    cd ${S}
    install -m 0644 ${WORKDIR}/gstomx.conf ${S}/config/rcar/
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_${PN} = "libwayland-egl"

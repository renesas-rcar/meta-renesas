SRC_URI = "git://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN3/1.2.0"

DEPENDS += "omx-user-module mmngrbuf-user-module"

SRCREV = "a809869c72b2790b6e52a8a04644264c26690f82"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=9281ffe981001da5a13db0303fa7c4ab \
"

S = "${WORKDIR}/git"

GSTREAMER_1_0_OMX_TARGET = "rcar"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libomxr_core.so"
EXTRA_OECONF_append = " --enable-experimental"
EXTRA_OECONF_append = " --with-omx-header-path=${S}/omx/openmax"

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_${PN} = "libwayland-egl"

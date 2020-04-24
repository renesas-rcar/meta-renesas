SRC_URI_remove = "http://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append = " gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3/1.16.0"

SRCREV = "7f334f733534a3a64d848df110da12af9650eaed"

DEPENDS += "weston"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

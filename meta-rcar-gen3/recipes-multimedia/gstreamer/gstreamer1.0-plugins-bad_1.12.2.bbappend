SRC_URI_remove = "http://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append = " gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3/1.12.2"

SRCREV = "db554fad172f2dabb0f7a75ef1e8e4cb35e172c9"

DEPENDS += "weston"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_gstreamer1.0-plugins-bad += "libwayland-egl"

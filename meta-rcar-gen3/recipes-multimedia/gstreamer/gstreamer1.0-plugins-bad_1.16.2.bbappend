SRC_URI_remove = "http://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append = " gitsm://git@osdg.renesas.com/rcar-gstreamer/gst-plugins-bad.git;protocol=ssh;branch=1.16.2_release"

SRCREV = "4459dc7686a2c20ed9097734e11d17631f71105a"

DEPENDS += "weston"

S = "${WORKDIR}/git"

RENESAS_GST_PLUGINS_BAD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3/1.16.3"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append = " ${RENESAS_GST_PLUGINS_BAD_URL}"

SRCREV = "3ef17d3c57e12f9d7536e464656b871a8949fa5b"

DEPENDS += "weston"

S = "${WORKDIR}/git"

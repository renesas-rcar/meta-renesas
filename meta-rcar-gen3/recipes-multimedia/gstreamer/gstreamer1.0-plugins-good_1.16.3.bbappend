RENESAS_GST_PLUGINS_GOOD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3e/1.16.3"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz"
SRC_URI_append = " ${RENESAS_GST_PLUGINS_GOOD_URL}"

SRCREV = "58f1eea1eeed684fc49c16d79a8cadfe3ad1f67c"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git"

EXTRA_OEMESON_append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

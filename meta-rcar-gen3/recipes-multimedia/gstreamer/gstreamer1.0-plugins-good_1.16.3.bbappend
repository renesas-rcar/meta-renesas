RENESAS_GST_PLUGINS_GOOD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3e/1.16.3;protocol=https;lfs=0"

SRC_URI_remove_rcar-gen3 = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz"
SRC_URI_append_rcar-gen3 = " ${RENESAS_GST_PLUGINS_GOOD_URL}"

SRCREV_rcar-gen3 = "58f1eea1eeed684fc49c16d79a8cadfe3ad1f67c"

DEPENDS_append_rcar-gen3 = " mmngrbuf-user-module"

S_rcar-gen3 = "${WORKDIR}/git"

EXTRA_OEMESON_append_rcar-gen3 = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

RENESAS_GST_PLUGINS_GOOD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3/1.16.3"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz"
SRC_URI_append = " ${RENESAS_GST_PLUGINS_GOOD_URL}"

SRCREV = "ca2036d2b09a434adc52aa02890c1503bf7100bf"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git"

EXTRA_OEMESON_append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

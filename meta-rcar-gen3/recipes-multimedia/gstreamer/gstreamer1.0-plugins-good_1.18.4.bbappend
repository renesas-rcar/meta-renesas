RENESAS_GST_PLUGINS_GOOD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3e/1.18.4"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
		  file://0001-qt-include-ext-qt-gstqtgl.h-instead-of-gst-gl-gstglf.patch \
           	  file://0002-rtpjitterbuffer-Fix-parsing-of-the-mediaclk-direct-f.patch \
                  "

SRC_URI_append = " ${RENESAS_GST_PLUGINS_GOOD_URL}"

SRCREV = "21479d8ca6bcd68326b172e08d089d48b3171366"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git"

EXTRA_OEMESON_append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

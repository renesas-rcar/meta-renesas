RENESAS_GST_PLUGINS_GOOD_URL ?= "gitsm://github.com/renesas-rcar/gstreamer.git;branch=RCAR-GEN3e/1.20.1;protocol=https"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
		  file://0001-qt-include-ext-qt-gstqtgl.h-instead-of-gst-gl-gstglf.patch \
           	  file://0002-rtpjitterbuffer-Fix-parsing-of-the-mediaclk-direct-f.patch \
                  "

SRC_URI:append = " ${RENESAS_GST_PLUGINS_GOOD_URL}"

SRCREV = "c8e94177fecb95bc0dcd9d86e8f67cdbc9d913eb"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git/subprojects/gst-plugins-good"

EXTRA_OEMESON:append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

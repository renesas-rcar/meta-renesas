RENESAS_GST_PLUGINS_GOOD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3e/1.18.5;protocol=https"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
		  file://0001-qt-include-ext-qt-gstqtgl.h-instead-of-gst-gl-gstglf.patch \
           	  file://0002-rtpjitterbuffer-Fix-parsing-of-the-mediaclk-direct-f.patch \
                  "

SRC_URI:append = " ${RENESAS_GST_PLUGINS_GOOD_URL}"

SRCREV = "66192b6f24ee3b803fccd7629e1423a50264eec2"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git"

EXTRA_OEMESON:append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

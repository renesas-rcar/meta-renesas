SRC_URI_remove = "http://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz"
SRC_URI_append = " gitsm://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3/1.16.2"

SRCREV = "5588c8b83e77d5419e498ada54eef0c183486172"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git"

EXTRA_OEMESON_append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

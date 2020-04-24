SRC_URI_remove = "http://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz"
SRC_URI_append = " gitsm://git@osdg.renesas.com/rcar-gstreamer/gst-plugins-good.git;protocol=ssh;branch=1.16.2_release"

SRCREV = "5588c8b83e77d5419e498ada54eef0c183486172"

DEPENDS += "mmngrbuf-user-module"

S = "${WORKDIR}/git"

EXTRA_OEMESON_append = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

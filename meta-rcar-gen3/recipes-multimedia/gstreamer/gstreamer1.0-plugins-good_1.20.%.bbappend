require ${@bb.utils.contains('MACHINEOVERRIDES', 'rcar-gen3', 'include/rcar-gen3-modules-common.inc', '', d)}
require gstreamer.inc

SRC_URI:remove:rcar-gen3 = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
		  file://0001-qt-include-ext-qt-gstqtgl.h-instead-of-gst-gl-gstglf.patch \
           	  file://0002-rtpjitterbuffer-Fix-parsing-of-the-mediaclk-direct-f.patch \
                  "

SRC_URI:append:rcar-gen3 = " ${RENESAS_GST_URL}"

DEPENDS:append:rcar-gen3 = " mmngrbuf-user-module"

S:rcar-gen3 = "${WORKDIR}/git/subprojects/gst-plugins-good"

EXTRA_OEMESON:append:rcar-gen3 = " \
    -Dcont-frame-capture=true \
    -Dignore-fps-of-video-standard=true \
"

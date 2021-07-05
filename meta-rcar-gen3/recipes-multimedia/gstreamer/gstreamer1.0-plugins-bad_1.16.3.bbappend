RENESAS_GST_PLUGINS_BAD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3e/1.16.3"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append = " ${RENESAS_GST_PLUGINS_BAD_URL}"

SRCREV = "0b00bd6365ddd87b05242d9d2b08955cf02e474e"

require include/rcar-gen3-modules-common.inc
DEPENDS += "weston libdrm mmngr-user-module linux-renesas"

S = "${WORKDIR}/git"

EXTRA_OECONF += "--enable-kms"
PACKAGECONFIG_append = "kms"

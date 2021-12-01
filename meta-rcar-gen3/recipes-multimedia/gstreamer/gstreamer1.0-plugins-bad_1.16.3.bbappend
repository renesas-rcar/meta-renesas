RENESAS_GST_PLUGINS_BAD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3e/1.16.3;protocol=https"

SRC_URI_remove = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append = " ${RENESAS_GST_PLUGINS_BAD_URL}"

SRCREV = "0c9bd4e3fe26ec32928d2a3e93b618857ff50e26"

require include/rcar-gen3-modules-common.inc
DEPENDS += "weston libdrm mmngr-user-module linux-renesas"

S = "${WORKDIR}/git"

EXTRA_OECONF += "--enable-kms"
PACKAGECONFIG_append = "kms"

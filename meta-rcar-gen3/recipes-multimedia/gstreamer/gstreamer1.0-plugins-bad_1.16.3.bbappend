RENESAS_GST_PLUGINS_BAD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3e/1.16.3;protocol=https;lfs=0"

SRC_URI_remove_rcar-gen3 = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"
SRC_URI_append_rcar-gen3 = " ${RENESAS_GST_PLUGINS_BAD_URL}"

SRCREV_rcar-gen3 = "0c9bd4e3fe26ec32928d2a3e93b618857ff50e26"

require ${@bb.utils.contains('MACHINEOVERRIDES', 'rcar-gen3', 'include/rcar-gen3-modules-common.inc', '', d)}
DEPENDS_append_rcar-gen3 = " weston libdrm mmngr-user-module linux-renesas"

S_rcar-gen3 = "${WORKDIR}/git"

EXTRA_OECONF_rcar-gen3 += "--enable-kms"
PACKAGECONFIG_append_rcar-gen3 = "kms"

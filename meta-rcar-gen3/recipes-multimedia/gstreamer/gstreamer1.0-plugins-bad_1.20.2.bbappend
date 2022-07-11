RENESAS_GST_PLUGINS_BAD_URL ?= "gitsm://github.com/renesas-rcar/gstreamer.git;branch=RCAR-GEN3e/1.20.1;protocol=https"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz \
                  file://0001-fix-maybe-uninitialized-warnings-when-compiling-with.patch \
                  file://0002-avoid-including-sys-poll.h-directly.patch \
                  file://0003-ensure-valid-sentinals-for-gst_structure_get-etc.patch \
                  file://0004-opencv-resolve-missing-opencv-data-dir-in-yocto-buil.patch \
                  file://0005-msdk-fix-includedir-path.patch \
                  "

SRC_URI:append = " ${RENESAS_GST_PLUGINS_BAD_URL}"

SRCREV = "c8e94177fecb95bc0dcd9d86e8f67cdbc9d913eb"

require include/rcar-gen3-modules-common.inc
DEPENDS += "weston libdrm"

S = "${WORKDIR}/git/subprojects/gst-plugins-bad"

EXTRA_OECONF += "--enable-kms"
PACKAGECONFIG:append = "kms"

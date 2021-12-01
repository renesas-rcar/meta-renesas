RENESAS_GST_PLUGINS_BAD_URL ?= "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3e/1.18.4;protocol=https"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz \
                  file://0001-fix-maybe-uninitialized-warnings-when-compiling-with.patch \
                  file://0002-avoid-including-sys-poll.h-directly.patch \
                  file://0003-ensure-valid-sentinals-for-gst_structure_get-etc.patch \
                  file://0004-opencv-resolve-missing-opencv-data-dir-in-yocto-buil.patch \
                  file://0005-msdk-fix-includedir-path.patch \
                  "

SRC_URI:append = " ${RENESAS_GST_PLUGINS_BAD_URL}"

SRCREV = "3d832a61b92c5a4e55ef38d4ce9f6f9af2b53a4b"

require include/rcar-gen3-modules-common.inc
DEPENDS += "weston libdrm"

S = "${WORKDIR}/git"

EXTRA_OECONF += "--enable-kms"
PACKAGECONFIG:append = "kms"

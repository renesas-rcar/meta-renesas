require ${@bb.utils.contains('MACHINEOVERRIDES', 'rcar-gen3', 'include/rcar-gen3-modules-common.inc', '', d)}
require gstreamer.inc

SRC_URI:remove:rcar-gen3 = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz \
                  file://0001-fix-maybe-uninitialized-warnings-when-compiling-with.patch \
                  file://0002-avoid-including-sys-poll.h-directly.patch \
                  file://0003-ensure-valid-sentinals-for-gst_structure_get-etc.patch \
                  file://0004-opencv-resolve-missing-opencv-data-dir-in-yocto-buil.patch \
                  file://0005-msdk-fix-includedir-path.patch \
                  "

SRC_URI:append:rcar-gen3 = " ${RENESAS_GST_URL}"


DEPENDS:append:rcar-gen3 = " weston libdrm"

S:rcar-gen3 = "${WORKDIR}/git/subprojects/gst-plugins-bad"

EXTRA_OECONF:rcar-gen3 += "--enable-kms"
PACKAGECONFIG:append:rcar-gen3 = "kms"

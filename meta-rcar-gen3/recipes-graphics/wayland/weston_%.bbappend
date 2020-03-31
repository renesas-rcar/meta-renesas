require include/gles-control.inc
require include/multimedia-control.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen3 = " \
    file://0001-drm-change-timing-to-set-color-format-for-primary-pl.patch \
"

PACKAGECONFIG_remove_virtclass-multilib-lib32 = "launch"

DEPENDS_append_rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', ' libgbm', '', d)}"

RDEPENDS_${PN}_append_rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', ' libgbm', '', d)} \
"
RDEPENDS_${PN}-examples_append_rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', ' libgbm', '', d)}"

EXTRA_OECONF_append_rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', '', \
        ' WESTON_NATIVE_BACKEND="fbdev-backend.so"', d)}"

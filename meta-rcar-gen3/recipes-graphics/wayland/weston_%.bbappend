require include/gles-control.inc
require include/multimedia-control.inc

PACKAGECONFIG_remove_virtclass-multilib-lib32 = "launch"
PACKAGECONFIG_CONFARGS_append_rcar-gen3 = " -Dsimple-dmabuf-drm=auto"

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

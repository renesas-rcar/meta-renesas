require include/gles-control.inc
require include/multimedia-control.inc

PACKAGECONFIG_remove_virtclass-multilib-lib32 = "launch"
DEPENDS_append = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"

RDEPENDS_${PN}_append = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)} \
"
RDEPENDS_${PN}-examples_append = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"

EXTRA_OECONF_append = " \
    ${@base_conditional('USE_GLES', '1', '', \
        ' WESTON_NATIVE_BACKEND="fbdev-backend.so"', d)}"

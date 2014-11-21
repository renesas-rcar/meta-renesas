require ../../include/gles-control.inc

PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

PACKAGECONFIG_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '', 'fbdev', d)}"
DEPENDS_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'gles-user-module', '', d)}"
EXTRA_OECONF_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '', \
        '--disable-xwayland-test WESTON_NATIVE_BACKEND=fbdev-backend.so', d)}"

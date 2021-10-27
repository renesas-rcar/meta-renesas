require include/gles-control.inc

DEPENDS:append:rcar-gen3 = " ${@oe.utils.conditional('USE_GLES_WAYLAND', '1', 'libegl', '', d)}"

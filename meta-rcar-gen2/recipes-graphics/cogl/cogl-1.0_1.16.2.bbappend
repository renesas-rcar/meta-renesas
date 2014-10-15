require ../../include/gles-control.inc
DEPENDS_append_rcar-gen2 = " \
    ${@'libegl' if '${USE_GLES_WAYLAND}' == '1'  else ''}"

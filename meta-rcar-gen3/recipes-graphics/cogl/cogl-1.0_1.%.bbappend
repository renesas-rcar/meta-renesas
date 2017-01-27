require include/gles-control.inc
DEPENDS_append = " \
    ${@'gles-user-module libgbm' if '${USE_GLES_WAYLAND}' == '1'  else ''} \
"

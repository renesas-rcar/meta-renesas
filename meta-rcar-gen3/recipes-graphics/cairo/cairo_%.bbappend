require include/gles-control.inc

DEPENDS_append = "${@ ' gles-user-module' if '${USE_GLES_WAYLAND}' == '1' else ''}"

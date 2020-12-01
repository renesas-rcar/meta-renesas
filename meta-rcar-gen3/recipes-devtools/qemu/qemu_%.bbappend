require include/gles-control.inc

PKGS_TO_REMOVE = "${@'virglrenderer glx' if '${USE_GLES_WAYLAND}' == '1'  else ''}"

PACKAGECONFIG_remove_class-nativesdk = "${PKGS_TO_REMOVE}"

require include/gles-control.inc

PKGS_TO_REMOVE = "${@'virglrenderer glx' if '${USE_GLES_WAYLAND}' == '1'  else ''}"

PACKAGECONFIG:remove:class-nativesdk = "${PKGS_TO_REMOVE}"

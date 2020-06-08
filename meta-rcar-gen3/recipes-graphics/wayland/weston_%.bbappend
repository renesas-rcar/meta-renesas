require include/gles-control.inc
require include/multimedia-control.inc

PACKAGECONFIG_remove_virtclass-multilib-lib32 = "launch"
PACKAGECONFIG_CONFARGS_append_rcar-gen3 = " -Dsimple-dmabuf-drm=auto"

EXTRA_OECONF_append_rcar-gen3 = " WESTON_NATIVE_BACKEND=fbdev-backend.so"

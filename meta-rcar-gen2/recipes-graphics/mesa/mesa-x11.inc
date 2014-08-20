# FIXME: can not override PACKAGECONFIG[*]
PACKAGECONFIG[gles] = "--enable-gles1 --disable-gles2"

PACKAGECONFIG_remove_rcar-gen2 = "egl"

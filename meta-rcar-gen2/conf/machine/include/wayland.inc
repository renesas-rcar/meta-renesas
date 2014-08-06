DISTRO_FEATURES_append = " wayland pam"

#Wayland uses RGX when it is enable
WAYLAND_GFX_ENABLE='${@"1" if ${GLES_ENABLE} == 1 else "0"}'
GLES_TP_ENABLE = "0"

include ${@'wayland-gfx.inc' if ${WAYLAND_GFX_ENABLE}==1 else 'x11.inc'}

# Fix version opengles2 for Wayland environment
OPENGLES3 = "0"

DISTRO_FEATURES_remove="x11"

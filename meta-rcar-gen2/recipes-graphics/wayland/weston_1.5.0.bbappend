PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

PACKAGECONFIG_append_rcar-gen2 = " \
        ${@base_contains('MACHINE_FEATURES', 'rgx', '', \
        base_contains('MACHINE_FEATURES', 'sgx', '', \
        'fbdev', d), d)}" 

DEPENDS_append_rcar-gen2 = " \
        ${@base_contains('MACHINE_FEATURES', 'rgx', 'gles-user-module', \
        base_contains('MACHINE_FEATURES', 'sgx', 'gles-user-module', \
        '', d), d)}" 

EXTRA_OECONF_append_rcar-gen2 = " \
        ${@base_contains('MACHINE_FEATURES', 'rgx', '', \
        base_contains('MACHINE_FEATURES', 'sgx', '', \
        '--disable-xwayland-test WESTON_NATIVE_BACKEND=fbdev-backend.so', d), d)}" 

# for renesas / FIXME
#export COMPOSITOR_LIBS="-lGLESv2 -lEGL  -lwayland-server -lxkbcommon -lpixman-1"
#export COMPOSITOR_CFLAGS="-I ${STAGING_DIR_HOST}/usr/include/pixman-1 -DLINUX=1 -DEGL_API_FB -DEGL_API_WL"

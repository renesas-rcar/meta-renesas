# FIXME: can not override PACKAGECONFIG.
PACKAGECONFIG[gles] = "--enable-gles1 --disable-gles2"
PACKAGECONFIG[egl] = "--enable-egl --with-egl-platforms=wayland"

# Disable the gbm modules of mesa
EXTRA_OECONF_rcar-gen2 := "${@'${EXTRA_OECONF}'.replace('--enable-gbm', '--disable-gbm')}"
# Remove the gbm and egl packages. These are provided in other recipes.
PACKAGES_remove_rcar-gen2 = "libgbm-dev libgbm libegl-mesa-dev libegl-mesa libegl-dev libegl libgles2-mesa libgles2-mesa-dev"

# Remove the x11 configure
PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

do_install_append_rcar-gen2() {
    # Remove libegl-mesa modules and headers
    rm -f ${D}/usr/lib/libEGL.la
    rm -f ${D}/usr/lib/libEGL.so*
    rm -f ${D}/usr/lib/pkgconfig/egl.pc
    rm -rf ${D}/usr/include/KHR
    rm -rf ${D}/usr/include/EGL
}

# FILES_${PN}-dbg += "${libdir}/src/debug/*"

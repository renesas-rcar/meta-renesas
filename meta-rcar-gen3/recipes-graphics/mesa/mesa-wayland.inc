# FIXME: can not override PACKAGECONFIG.
PACKAGECONFIG[gles] = "--enable-gles1 --disable-gles2"
PACKAGECONFIG[egl] = "--enable-egl --with-egl-platforms=wayland"

# Disable the gbm modules of mesa
PACKAGECONFIG_remove = "gbm"

# Remove the gbm and egl packages. These are provided in other recipes.
PACKAGES_remove = " \
    libgbm-dev libgbm \
    libegl-mesa-dev libegl-mesa \
    libegl-dev libegl \
    libgles2-mesa libgles2-mesa-dev \
"
INSTALLED_HEADER = "src/egl/wayland/wayland-egl/wayland-egl-priv.h"

do_install_append() {
    # Remove libegl-mesa modules and headers
    rm -f ${D}/${libdir}/libEGL.la
    rm -f ${D}/${libdir}/libEGL.so*
    rm -f ${D}/${libdir}/pkgconfig/egl.pc
    rm -rf ${D}/${includedir}/EGL
    rm -rf ${D}/${includedir}/KHR

    # install required header for wayland
    install -d ${D}/usr/include
    install -m 644 ${S}/${INSTALLED_HEADER} ${D}/usr/include/
}

FILES_libwayland-egl-dev += " \
    /usr/include \
    /usr/include/*.h \
"

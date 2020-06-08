# FIXME: can not override PACKAGECONFIG.
PACKAGECONFIG[gles] = "-Dgles1=true -Dgles2=false"
PACKAGECONFIG[egl] = "-Degl=true"

PROVIDES_append = " ${@bb.utils.contains('PACKAGECONFIG', 'gbm', 'libgbm', '', d)}"

# Remove the gbm and egl packages. These are provided in other recipes.
PACKAGES_remove = " \
    libegl-mesa-dev libegl-mesa \
    libegl-dev libegl \
    libgles2-mesa libgles2-mesa-dev \
"

do_install_append() {
    # Remove libegl-mesa modules and headers
    rm -f ${D}/${libdir}/libEGL.la
    rm -f ${D}/${libdir}/libEGL.so*
    rm -f ${D}/${libdir}/pkgconfig/egl.pc
    rm -rf ${D}/${includedir}/EGL
    rm -rf ${D}/${includedir}/KHR
}

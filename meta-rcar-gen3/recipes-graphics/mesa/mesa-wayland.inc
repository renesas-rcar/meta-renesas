# FIXME: can not override PACKAGECONFIG.
PACKAGECONFIG[gles] = "-Dgles1=true -Dgles2=false, -Dgles1=false -Dgles2=false"

PACKAGES_remove = " \
    libegl-mesa-dev libegl-mesa \
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

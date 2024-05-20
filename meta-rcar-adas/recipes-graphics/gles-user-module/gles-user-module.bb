DESCRIPTION = "PowerVR GPU user module"
LICENSE = "CLOSED"

require include/rcar-bsp-modules-common.inc
require include/rcar-bsp-path-common.inc

PN = "gles-user-module"
PR = "r0"

COMPATIBLE_MACHINE = "whitehawk|grayhawk"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "kernel-module-gles"

S = "${WORKDIR}/rogue"
GLES = "gsx"

PROVIDES = "virtual/gles-user-module virtual/egl virtual/libgles2"

require include/rcar-gfx-common.inc

SRC_URI:r8a779g0 = "${GFX_URL}/raw/${BRANCH}/opengl/r8a779g0_linux_gsx_binaries_gles.tar.bz2;\
sha256sum=007cf0086ef58314511320977b1568064fbcad0d42f83543b588a2c09d39512a"

SRC_URI:r8a779h0 = "${GFX_URL}/raw/${BRANCH}/opengl/r8a779h0_linux_gsx_binaries_gles.tar.bz2;\
sha256sum=3f5a616360d98cd5e36f69787470b71fef7a4c1ad11f68d99b8b04317609a011"

SRC_URI:append = " file://rc.pvr.service"

inherit systemd

SYSTEMD_SERVICE:${PN} = "rc.pvr.service"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Install configuration files
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 644 ${S}/etc/udev/rules.d/72-pvr-seat.rules ${D}${sysconfdir}/udev/rules.d/

    # Install header files
    install -d ${D}${includedir}/EGL
    install -m 644 ${S}/usr/include/EGL/*.h ${D}${includedir}/EGL/
    install -d ${D}${includedir}/GLES2
    install -m 644 ${S}/usr/include/GLES2/*.h ${D}${includedir}/GLES2/
    install -d ${D}${includedir}/GLES3
    install -m 644 ${S}/usr/include/GLES3/*.h ${D}${includedir}/GLES3/
    install -d ${D}${includedir}/KHR
    install -m 644 ${S}/usr/include/KHR/khrplatform.h ${D}${includedir}/KHR/khrplatform.h

    # Install pre-builded binaries
    install -d ${D}${libdir}
    install -m 755 ${S}/usr/lib/*.so ${D}${libdir}/
    install -d ${D}${RENESAS_DATADIR}/bin
    install -m 755 ${S}/usr/local/bin/dlcsrv_REL ${D}${RENESAS_DATADIR}/bin/dlcsrv_REL
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 644 ${S}/lib/firmware/* ${D}${nonarch_base_libdir}/firmware/

    # Install pkgconfig
    install -d ${D}${libdir}/pkgconfig
    install -m 644 ${S}/usr/lib/pkgconfig/*.pc ${D}${libdir}/pkgconfig/

    # Create symbolic link
    cd ${D}${libdir}
    ln -s libEGL.so libEGL.so.1
    ln -s libGLESv2.so libGLESv2.so.2

    # Install systemd service
    install -d ${D}${systemd_system_unitdir}/
    install -m 644 ${WORKDIR}/rc.pvr.service ${D}${systemd_system_unitdir}/
    install -d ${D}${exec_prefix}/bin
    install -m 755 ${S}/etc/init.d/rc.pvr ${D}${exec_prefix}/bin/pvrinit
}

PACKAGES = "\
    ${PN} \
    libegl-${PN} \
    libgles2-${PN} \
    ${PN}-dev \
    libegl-${PN}-dev \
    libgles2-${PN}-dev \
"

FILES:${PN} = " \
    ${sysconfdir}/* \
    ${libdir}/libdlc_REL.so* \
    ${libdir}/libIMGegl.so* \
    ${libdir}/libpvrDRM_WSEGL.so* \
    ${libdir}/libPVRScopeServices.so* \
    ${libdir}/libsrv_um.so* \
    ${libdir}/libufwriter.so* \
    ${libdir}/libusc.so* \
    ${nonarch_base_libdir}/firmware/rgx.fw* \
    ${nonarch_base_libdir}/firmware/rgx.sh* \
    ${RENESAS_DATADIR}/bin/* \
    ${exec_prefix}/bin/* \
"
FILES:${PN}:append:r8a779h0 = " ${libdir}/libglslcompiler.so*"
FILES:libegl-${PN} = "${libdir}/libEGL.so*"
FILES:libgles2-${PN} = "${libdir}/libGLESv2.so*"

FILES:${PN}-dev = " \
    ${includedir}/* \
    ${libdir}/pkgconfig/* \
"
FILES:libegl-${PN}-dev = " \
    ${libdir}/libEGL.* \
    ${includedir}/EGL \
    ${includedir}/KHR/khrplatform.h \
    ${libdir}/pkgconfig/egl.pc \
"
FILES:libgles2-${PN}-dev = " \
    ${libdir}/libGLESv2.* \
    ${includedir}/GLES2 \
    ${libdir}/pkgconfig/glesv2.pc \
"
FILES:libgles3-${PN}-dev = " \
    ${includedir}/GLES3 \
"

RPROVIDES:libegl-${PN} = "libegl"
RPROVIDES:libegl-${PN}-dev = "libegl-dev"
RPROVIDES:libgles2-${PN} = "libgles2"
RPROVIDES:libgles2-${PN}-dev = "libgles2-dev"
RPROVIDES:libgles3-${PN}-dev = "libgles3-dev"

RDEPENDS:${PN} = " \
    kernel-module-gles \
"

INSANE_SKIP:${PN} = "ldflags build-deps file-rdeps"
INSANE_SKIP:${PN}-dev = "ldflags build-deps file-rdeps"
INSANE_SKIP:${PN} += "arch"
INSANE_SKIP:${PN}-dev += "arch"
INSANE_SKIP:${PN}-dbg = "arch"

#To avoid already-stripped errors and not stripped libs from packages
INSANE_SKIP:${PN} += "already-stripped"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"


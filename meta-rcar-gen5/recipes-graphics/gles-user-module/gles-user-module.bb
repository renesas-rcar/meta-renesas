DESCRIPTION = "PowerVR GPU user module"
LICENSE = "CLOSED"

require include/rcar-bsp-modules-common.inc
require include/rcar-bsp-path-common.inc

PN = "gles-user-module"
PR = "r0"

COMPATIBLE_MACHINE = "x5h_vpf|x5h_evb|ironhide|perceptor"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "kernel-module-gles"

S = "${UNPACKDIR}/rogue"
GLES = "gsx"

PROVIDES = "virtual/gles-user-module virtual/egl virtual/libgles2"

require include/rcar-gfx-common.inc

SRC_URI:rcar-gen5-evb = "${GFX_URL}/raw/${BRANCH}/opengl/r8a78000_linux_gsx_binaries_gles.tar.bz2;\
sha256sum=8fb921897c41c07969f949c84dcfd464be2906e91dfca13d4309ea2e145ba67b"

SRC_URI:rcar-gen5-vpf = "${GFX_URL}/raw/${BRANCH}/opengl/r8a78000_linux_gsx_binaries_gles.tar.bz2;\
sha256sum=85ad8645b856bef8963d7459529a6e5ce51b847469790588e9732275e12b8b6e"

SRC_URI:append = " \
    file://rc.pvr.service \
    file://pvrinit \
"

inherit systemd

SYSTEMD_SERVICE:${PN} = "rc.pvr.service"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Install configuration files
    install -d ${D}${sysconfdir}/udev/rules.d
    install -d ${D}${sysconfdir}/vulkan/icd.d
    install -d ${D}${sysconfdir}/OpenCL/vendors
    install -m 644 ${S}/etc/udev/rules.d/72-pvr-seat.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 644 ${S}/etc/powervr.ini ${D}${sysconfdir}
    install -m 644 ${S}/etc/vulkan/icd.d/powervr_icd.json ${D}${sysconfdir}/vulkan/icd.d/
    install -m 644 ${S}/etc/OpenCL/vendors/IMG.icd ${D}${sysconfdir}/OpenCL/vendors/

    # Install header files
    install -d ${D}${includedir}/EGL
    install -m 644 ${S}/usr/include/EGL/*.h ${D}${includedir}/EGL/
    install -d ${D}${includedir}/GLES
    install -m 644 ${S}/usr/include/GLES/*.h ${D}${includedir}/GLES/
    install -d ${D}${includedir}/GLES2
    install -m 644 ${S}/usr/include/GLES2/*.h ${D}${includedir}/GLES2/
    install -d ${D}${includedir}/GLES3
    install -m 644 ${S}/usr/include/GLES3/*.h ${D}${includedir}/GLES3/
    install -d ${D}${includedir}/KHR
    install -m 644 ${S}/usr/include/KHR/khrplatform.h ${D}${includedir}/KHR/khrplatform.h
    install -d ${D}${includedir}/CL
    install -m 644 ${S}/usr/include/CL/*.h ${D}${includedir}/CL/
    install -d ${D}${includedir}/vulkan
    install -m 644 ${S}/usr/include/vulkan/* ${D}${includedir}/vulkan/

    # Install additional header files
    install -d ${D}${includedir}/drv/CL
    install -m 644 ${S}/usr/include/drv/CL/*.h ${D}${includedir}/drv/CL/
    install -d ${D}${includedir}/drv/EGL
    install -m 644 ${S}/usr/include/drv/EGL/*.h ${D}${includedir}/drv/EGL/
    install -d ${D}${includedir}/drv/GLES
    install -m 644 ${S}/usr/include/drv/GLES/*.h ${D}${includedir}/drv/GLES/
    install -d ${D}${includedir}/drv/GLES3
    install -m 644 ${S}/usr/include/drv/GLES3/*.h ${D}${includedir}/drv/GLES3/
    install -d ${D}${includedir}/spirv
    install -m 644 ${S}/usr/include/spirv/* ${D}${includedir}/spirv/
    install -d ${D}${includedir}/vk_video
    install -m 644 ${S}/usr/include/vk_video/*.h ${D}${includedir}/vk_video/

    # Install pre-built binaries
    install -d ${D}${libdir}
    install -d ${D}${libdir}/cmake/VulkanLoader
    install -m 755 ${S}/usr/lib/*.so* ${D}${libdir}/
    install -d ${D}${RENESAS_DATADIR}/bin
    install -m 755 ${S}/usr/local/bin/dlcsrv_REL ${D}${RENESAS_DATADIR}/bin/dlcsrv_REL
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 644 ${S}/lib/firmware/* ${D}${nonarch_base_libdir}/firmware/
    install -m 644 ${S}/usr/lib/cmake/VulkanLoader/* ${D}${libdir}/cmake/VulkanLoader/

    # Install pkgconfig
    install -d ${D}${libdir}/pkgconfig
    install -m 644 ${S}/usr/lib/pkgconfig/*.pc ${D}${libdir}/pkgconfig/
    install -m 644 ${S}/usr/lib/pkgconfig/*.pc ${D}${libdir}/pkgconfig/

    # Create symbolic link
    cd ${D}${libdir}
    ln -s libEGL.so libEGL.so.1
    ln -s libGLESv2.so libGLESv2.so.2
    ln -s libPVROCL.so libPVROCL.so.1

    # Install systemd service
    install -d ${D}${systemd_system_unitdir}/
    install -m 644 ${UNPACKDIR}/rc.pvr.service ${D}${systemd_system_unitdir}/
    install -d ${D}${exec_prefix}/bin
    install -m 755 ${UNPACKDIR}/pvrinit ${D}${exec_prefix}/bin/
}

PACKAGES = "\
    ${PN} \
    libegl-${PN} \
    libgles2-${PN} \
    ${PN}-dev \
    libegl-${PN}-dev \
    libgles-${PN}-dev \
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
    ${libdir}/libPVROCL.so* \
    ${libdir}/libOpenCL.so* \
    ${libdir}/libvulkan.so* \
    ${libdir}/libvulkan-1.so \
    ${libdir}/libVK_IMG.so \
    ${libdir}/libsutu_display.so \
    ${nonarch_base_libdir}/firmware/rgx.fw* \
    ${nonarch_base_libdir}/firmware/rgx.sh* \
    ${RENESAS_DATADIR}/bin/* \
    ${exec_prefix}/bin/* \
    ${libdir}/cmake/VulkanLoader/* \
"
FILES:libegl-${PN} = "${libdir}/libEGL.so*"
FILES:libgles2-${PN} = "${libdir}/libGLESv2.so*"

FILES:${PN}-dev = " \
    ${includedir}/* \
    ${libdir}/pkgconfig/* \
    ${libdir}/cmake/VulkanLoader/* \
"
FILES:libegl-${PN}-dev = " \
    ${libdir}/libEGL.* \
    ${includedir}/EGL \
    ${includedir}/KHR/khrplatform.h \
    ${libdir}/pkgconfig/egl.pc \
"
FILES:libgles-${PN}-dev = " \
    ${includedir}/GLES \
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
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm wayland-kms', '', d)} \
"

INSANE_SKIP:${PN} = "ldflags build-deps file-rdeps"
INSANE_SKIP:${PN}-dev = "ldflags build-deps file-rdeps"
INSANE_SKIP:${PN} += "arch"
INSANE_SKIP:${PN}-dev += "arch"
INSANE_SKIP:${PN}-dbg = "arch"

#To avoid already-stripped errors and not stripped libs from packages
INSANE_SKIP:${PN} += "already-stripped installed-vs-shipped"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"


require include/gles-control.inc
require include/rcar-gen3-path-common.inc

DESCRIPTION = "PowerVR GPU user module"
LICENSE = "CLOSED"

PN = "gles-user-module"
PR = "r0"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/rogue"
GLES = "gsx"

SRC_URI:r8a7795 = "file://r8a77951_linux_gsx_binaries_gles.tar.bz2"
SRC_URI:r8a7796 = "file://r8a77960_linux_gsx_binaries_gles.tar.bz2"
SRC_URI:r8a77965 = "file://r8a77965_linux_gsx_binaries_gles.tar.bz2"
SRC_URI:r8a77990 = "file://r8a77990_linux_gsx_binaries_gles.tar.bz2"

SRC_URI:append = " \
    file://rc.pvr.service \
"

inherit update-rc.d systemd

INITSCRIPT_NAME = "pvrinit"
INITSCRIPT_PARAMS = "start 7 5 2 . stop 62 0 1 6 ."
SYSTEMD_SERVICE:${PN} = "rc.pvr.service"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Install configuration files
    install -d ${D}${sysconfdir}/init.d
    install -m 644 ${S}/etc/powervr.ini ${D}${sysconfdir}
    install -m 755 ${S}/etc/init.d/rc.pvr ${D}${sysconfdir}/init.d/pvrinit
    install -m 755 ${S}/etc/init.d/rc.pvr ${D}${sysconfdir}/init.d/
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

    if [ "${USE_GLES_WAYLAND}" = "1" ]; then
        # Set the "WindowSystem" parameter for wayland
        if [ "${GLES}" = "gsx" ]; then
            sed -i -e "s/WindowSystem=libpvrDRM_WSEGL.so/WindowSystem=libpvrWAYLAND_WSEGL.so/g" \
                ${D}${sysconfdir}/powervr.ini
        fi
    fi

    # Install systemd service
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)} ; then
        install -d ${D}${systemd_system_unitdir}/
        install -m 644 ${WORKDIR}/rc.pvr.service ${D}${systemd_system_unitdir}/
        install -d ${D}${exec_prefix}/bin
        install -m 755 ${S}/etc/init.d/rc.pvr ${D}${exec_prefix}/bin/pvrinit
    fi
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES:${PN} = " \
    ${sysconfdir}/* \
    ${libdir}/* \
    ${nonarch_base_libdir}/firmware/rgx.fw* \
    ${nonarch_base_libdir}/firmware/rgx.sh* \
    ${RENESAS_DATADIR}/bin/* \
    ${exec_prefix}/bin/* \
"

FILES:${PN}-dev = " \
    ${includedir}/* \
    ${libdir}/pkgconfig/* \
"

PROVIDES = "virtual/libgles2"
RPROVIDES:${PN} += " \
    ${GLES}-user-module \
    libgles2-mesa \
    libgles2-mesa-dev \
    libgles2 \
    libgles2-dev \
"

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
INSANE_SKIP:${PN} += "already-stripped"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

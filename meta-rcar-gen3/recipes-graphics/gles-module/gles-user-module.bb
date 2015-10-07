require ../../include/gles-control.inc

DESCRIPTION = "PowerVR GPU user module"
LICENSE = "CLOSED"

PN = "gles-user-module"
PR = "r0"

COMPATIBLE_MACHINE = "r8a7795"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S_r8a7795 = "${WORKDIR}/rogue"
GLES_r8a7795 = "gsx"

SRC_URI_r8a7795 = "file://r8a7795_linux_gsx_binaries_gles3.tar.bz2"
SRC_URI_append_r8a7795 = " \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", " \
    file://EGL_headers_for_wayland.patch \
    file://change-shell.patch \
    file://rc.pvr.service \
    ", "", d)} \
"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Copy binary into sysroot
    mkdir ${D}/${exec_prefix}
    cp -r ${S}/${sysconfdir} ${D}
    cp -r ${S}/${exec_prefix}/local ${D}/${exec_prefix}
    cp -r ${S}/${includedir} ${D}/${exec_prefix}
    cp -r ${S}/${libdir} ${D}/${exec_prefix}

    if [ "${USE_WAYLAND}" = "1" ]; then
        # Rename libEGL.so
        mv ${D}/${libdir}/libEGL.so ${D}/${libdir}/libEGL-pvr.so

        # Set the "WindowSystem" parameter for wayland
        if [ "${GLES}" = "gsx" ]; then
            sed -i -e "s/WindowSystem=libpvrDRM_WSEGL.so/WindowSystem=libpvrWAYLAND_WSEGL.so/g" \
                ${D}/${sysconfdir}/powervr.ini
        fi
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
         install -d ${D}${systemd_unitdir}/system/
         install -m 0644 ${WORKDIR}/rc.pvr.service ${D}${systemd_unitdir}/system/
    fi
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    ${sysconfdir}/* \
    ${libdir}/* \
    /usr/local/bin/* \
"

FILES_${PN}-dev = " \
    ${includedir}/* \
"

inherit update-rc.d systemd

PROVIDES = "virtual/libgles2"
PROVIDES_append = " \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "", " virtual/egl", d)} \
"
RPROVIDES_${PN} += " \
    ${GLES}-user-module \
    libgles2-mesa \
    libgles2-mesa-dev \
    libgles2 \
    libgles2-dev \
"

RDEPENDS_${PN} += " kernel-module-gles \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", \
    " libegl libegl-dev", "", d)} \
"

INSANE_SKIP_${PN} += "ldflags build-deps file-rdeps already-stripped"
INSANE_SKIP_${PN}-dev += "ldflags build-deps file-rdeps"
INSANE_SKIP_${PN} += "arch"
INSANE_SKIP_${PN}-dev += "arch"
INSANE_SKIP_${PN}-dbg += "arch"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PRIVATE_LIBS_${PN} = "libEGL.so.1"
INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "start 7 5 2 . stop 62 0 1 6 ."
SYSTEMD_SERVICE_${PN} = "rc.pvr.service"

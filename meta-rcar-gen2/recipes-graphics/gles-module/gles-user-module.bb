require ../../include/gles-control.inc

DESCRIPTION = "SGX/RGX user module"
LICENSE = "CLOSED"

PN = "gles-user-module"
PR = "r0"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S_r8a7790 = "${WORKDIR}/rogue"
GLES_r8a7790 = "rgx"
S_r8a7791 = "${WORKDIR}/eurasia"
GLES_r8a7791 = "sgx"
S_r8a7793 = "${WORKDIR}/eurasia"
GLES_r8a7793 = "sgx"
S_r8a7794 = "${WORKDIR}/eurasia"
GLES_r8a7794 = "sgx"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

OPENGLES3 ?= "0"
SRC_URI_r8a7790 = '${@base_conditional( "OPENGLES3", "1", \
        "file://r8a7790_linux_rgx_binaries_gles3.tar.bz2", \
        "file://r8a7790_linux_rgx_binaries_gles2.tar.bz2", d )}'
SRC_URI_append_r8a7790 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
        file://EGL_headers_for_wayland.patch \
        file://change-shell.patch \
        file://rgx-user-module.pc \
        ", "", d)}"

SRC_URI_r8a7791 = "file://r8a7791_linux_sgx_binaries_gles2.tar.bz2"
SRC_URI_append_r8a7791 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
        file://EGL_headers_for_wayland.patch \
        file://sgx-user-module.pc \
        ", "", d)}"

SRC_URI_r8a7793 = "file://r8a7791_linux_sgx_binaries_gles2.tar.bz2"
SRC_URI_append_r8a7793 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
        file://EGL_headers_for_wayland.patch \
        file://sgx-user-module.pc \
        ", "", d)}"

SRC_URI_r8a7794 = "file://r8a7794_linux_sgx_binaries_gles2.tar.bz2"
SRC_URI_append_r8a7794 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
        file://EGL_headers_for_wayland.patch \
        file://sgx-user-module.pc \
        ", "", d)}"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Copy binary into sysroot
    cp -r ${S}/etc ${D}
    cp -r ${S}/usr ${D}

    if [ "${USE_WAYLAND}" = "1" ]; then
        # Install additional file
        mkdir -p ${D}/usr/lib/pkgconfig/
        install -m 0644 ${WORKDIR}/${GLES}-user-module.pc ${D}/usr/lib/pkgconfig/
    
        # Rename libEGL.so
        mv ${D}/usr/lib/libEGL.so ${D}/usr/lib/libEGL-pvr.so

        # Set the "WindowSystem" parameter for wayland
        if [ "${GLES}" = "rgx" ]; then
           sed -i -e "s/WindowSystem=libpvrNULL_WSEGL.so/WindowSystem=libpvrWAYLAND_WSEGL.so/g" \
           ${D}/${sysconfdir}/powervr.ini
        elif [ "${GLES}" = "sgx" ]; then
           sed -i -e "s/WindowSystem=libpvrPVR2D_FLIPWSEGL.so/WindowSystem=libpvrPVR2D_WAYLANDWSEGL.so/g" \
           ${D}/${sysconfdir}/powervr.ini
        fi
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

inherit update-rc.d

PROVIDES = "virtual/libgles2"
PROVIDES_append = "${@base_contains("DISTRO_FEATURES", "wayland", "", " virtual/egl", d)}"
RPROVIDES_${PN} += "${GLES}-user-module libgles2-mesa libgles2-mesa-dev libgles2 libgles2-dev"
INSANE_SKIP_${PN} += "ldflags already-stripped"
INSANE_SKIP_${PN}-dev += "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PRIVATE_LIBS_${PN} = "libEGL.so.1"
INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "start 8 5 2 . stop 21 0 1 6 ."

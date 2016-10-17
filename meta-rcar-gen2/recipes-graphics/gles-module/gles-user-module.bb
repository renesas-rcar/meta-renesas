require ../../include/gles-control.inc

DESCRIPTION = "SGX/RGX user module"
LICENSE = "CLOSED"

PN = "gles-user-module"
PR = "r2"

COMPATIBLE_MACHINE = "(wheat|blanche)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} += " kernel-module-gles \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", " libdrm wayland-kms libgbm", "", d)} \
"

S_r8a7791 = "${WORKDIR}/eurasia"
GLES_r8a7791 = "sgx"
S_r8a7792 = "${WORKDIR}/eurasia"
GLES_r8a7792 = "sgx"
S_r8a7794 = "${WORKDIR}/eurasia"
GLES_r8a7794 = "sgx"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

OPENGLES3 ?= "0"

SRC_URI_r8a7791 = "file://r8a7743_linux_sgx_binaries_gles2.tar.bz2"
SRC_URI_append_r8a7791 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
    file://EGL_headers_for_wayland.patch \
    ", "", d)}"

SRC_URI_r8a7792 = "file://r8a7792_linux_sgx_binaries_gles2.tar.bz2"
SRC_URI_append_r8a7792 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
    file://EGL_headers_for_wayland.patch \
    ", "", d)}"

SRC_URI_r8a7794 = "file://r8a7794_linux_sgx_binaries_gles2.tar.bz2"
SRC_URI_append_r8a7794 = " ${@base_contains("DISTRO_FEATURES", "wayland", " \
    file://EGL_headers_for_wayland.patch \
    ", "", d)}"

SRC_URI_append = " file://egl.pc file://glesv2.pc "

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Copy binary into sysroot
    cp -r ${S}/etc ${D}
    cp -r ${S}/usr ${D}

    if [ "${USE_WAYLAND}" = "1" ]; then
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

INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PRIVATE_LIBS_${PN} = "libEGL.so.1"
INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "start 7 5 2 . stop 62 0 1 6 ."




SUMMARY = "EGL library"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://egl.c;beginline=5;endline=15;md5=3677623633a6e459b1f60b1e541c4212"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"

PROVIDES = "${@base_contains("DISTRO_FEATURES", "wayland", "virtual/egl", "", d)}"
SRCREV = "71938547dc14393dc7ce77a48a23180056faf6a3"
SRC_URI = "git://github.com/thayama/libegl;protocol=git;branch=master"
SRC_URI_append_r8a7790 = " file://Makefile.am.rgx.patch \
        file://configure.ac.rgx.patch"
SRC_URI_append_r8a7791 = " file://Makefile.am.sgx.patch \
        file://configure.ac.sgx.patch"
SRC_URI_append_r8a7793 = " file://Makefile.am.sgx.patch \
        file://configure.ac.sgx.patch"
SRC_URI_append_r8a7794 = " file://Makefile.am.sgx.patch \
        file://configure.ac.sgx.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS = "libgbm gles-user-module"

do_compile_prepend() {
    # Remove unnecessary header files.
    # They are defined rgx/sgx-user-module.
    rm -rf ${S}/include/GLES2/
    rm -rf ${S}/include/KHR/
    rm -rf ${S}/include/EGL/egl.h
    rm -rf ${S}/include/EGL/eglext.h
    rm -rf ${S}/include/EGL/eglplatform.h
}

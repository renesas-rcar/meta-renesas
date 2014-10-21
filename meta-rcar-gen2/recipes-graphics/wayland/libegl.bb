SUMMARY = "EGL library"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://egl.c;beginline=5;endline=15;md5=3677623633a6e459b1f60b1e541c4212"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"

PROVIDES = "${@base_contains("DISTRO_FEATURES", "wayland", "virtual/egl", "", d)}"
SRCREV = "71938547dc14393dc7ce77a48a23180056faf6a3"
SRC_URI = "git://github.com/thayama/libegl;protocol=git;branch=master \
           file://0001-libegl-Remove-duplicate-header-files-of-gles-user-mo.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS = "libgbm gles-user-module"

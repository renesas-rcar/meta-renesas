SUMMARY = "EGL library"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = "file://egl.c;beginline=5;endline=15;md5=3677623633a6e459b1f60b1e541c4212"

COMPATIBLE_MACHINE = "(blanche|wheat)"

PROVIDES = "${@base_contains("DISTRO_FEATURES", "wayland", "virtual/egl", "", d)}"
SRCREV = "7b09cce97e8658ba927e71f1af43360c4cc392b7"
SRC_URI = " \
    git://github.com/thayama/libegl;protocol=git;branch=master \
    file://0001-libegl-Remove-duplicate-header-files-of-gles-user-mo.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS = "libgbm gles-user-module"

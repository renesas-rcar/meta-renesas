SUMMARY = "gbm library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://gbm.c;beginline=4;endline=22;md5=5cdaac262c876e98e47771f11c7036b5"
SECTION = "libs"

inherit autotools pkgconfig

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"

DEPENDS = "wayland-kms udev"

SRCREV = "538889dee7940cbcd8f384ff24436c785181cfdb"
SRC_URI = "git://github.com/renesas-rcar/libgbm;branch=match-mesa-20.0.1;protocol=https \
           file://Add-gbm_bo_get_fd_for_plane.patch \
"

S = "${WORKDIR}/git"

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
    ${PN}-staticdev \
"

FILES:${PN} = " \
    ${libdir}/libgbm.so.* \
    ${libdir}/gbm/libgbm_kms.so.* \
    ${libdir}/gbm/*.so \
    ${libdir}/*.so \
"
FILES:${PN}-dev += "${libdir}/gbm/*.la"
FILES:${PN}-dbg += "${libdir}/gbm/.debug/*"
FILES:${PN}-staticdev += "${libdir}/gbm/*.a"

INSANE_SKIP:${PN} += "dev-so"
PROVIDES += "virtual/libgbm"

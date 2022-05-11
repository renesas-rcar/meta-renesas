SUMMARY = "gbm library"
LICENSE = "MIT"
SECTION = "libs"

LIC_FILES_CHKSUM = " \
    file://gbm.c;beginline=4;endline=22;md5=5cdaac262c876e98e47771f11c7036b5"

SRCREV = "538889dee7940cbcd8f384ff24436c785181cfdb"
SRC_URI = "git://github.com/renesas-rcar/libgbm;branch=match-mesa-20.0.1;protocol=https"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"
DEPENDS = "wayland-kms udev"

inherit autotools pkgconfig

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

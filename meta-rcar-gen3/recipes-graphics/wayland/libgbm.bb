SUMMARY = "gbm library"
LICENSE = "MIT"
SECTION = "libs"

LIC_FILES_CHKSUM = " \
    file://gbm.c;beginline=4;endline=22;md5=5cdaac262c876e98e47771f11c7036b5"

SRCREV = "84eaeff0b0eec200f4d6473f1343a13625019d88"
SRC_URI = "git://github.com/thayama/libgbm;protocol=git;branch=master"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "r8a7795"
DEPENDS = "wayland-kms"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/libgbm.so.* ${libdir}/gbm/libgbm_kms.so.*"
FILES_${PN}-dev += "${libdir}/gbm/*.so ${libdir}/gbm/*.la"
FILES_${PN}-dbg += "${libdir}/gbm/.debug/*"
FILES_${PN}-staticdev += "${libdir}/gbm/*.a"

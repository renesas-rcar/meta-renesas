SUMMARY = "gbm library"
LICENSE = "MIT"
SECTION = "libs"

LIC_FILES_CHKSUM = " \
    file://gbm.c;beginline=4;endline=22;md5=5cdaac262c876e98e47771f11c7036b5"

SRCREV = "e74b2b92b66e3657acab25cdc23b3a4a96cea259"
SRC_URI = "git://github.com/thayama/libgbm;protocol=git;branch=master"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "r8a7795"
DEPENDS = "wayland-kms"

inherit autotools pkgconfig

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
    ${PN}-staticdev \
"

FILES_${PN} = "${libdir}/libgbm.so.* \
    ${libdir}/gbm/libgbm_kms.so.* \
    ${libdir}/gbm/*.so \
    ${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/gbm/*.la"
FILES_${PN}-dbg += "${libdir}/gbm/.debug/*"
FILES_${PN}-staticdev += "${libdir}/gbm/*.a"

INSANE_SKIP_${PN} += "dev-so"

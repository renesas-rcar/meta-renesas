SUMMARY = "gbm library"
LICENSE = "MIT"
SECTION = "libs"

LIC_FILES_CHKSUM = " \
    file://gbm.c;beginline=4;endline=22;md5=5cdaac262c876e98e47771f11c7036b5"

SRCREV = "c4ee70a7202dc81eaa45a73535e82cee98db2a0d"
SRC_URI = "git://github.com/renesas-rcar/libgbm;branch=rcar-gen3"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(blanche|wheat)"
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

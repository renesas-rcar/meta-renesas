SUMMARY = "KMS library for Wayland"
LICENSE = "MIT"

LIC_FILES_CHKSUM = " \
    file://wayland-kms.c;beginline=6;endline=24;md5=5cdaac262c876e98e47771f11c7036b5"

PV_append = "+git${SRCREV}"

SRCREV = "a1539fe24125d581c53a18e1e6b6d4958f672a77"
SRC_URI = "git://github.com/renesas-rcar/wayland-kms.git;branch=rcar-gen3"

COMPATIBLE_MACHINE = "(blanche|wheat)"
S = "${WORKDIR}/git"
DEPENDS = "libdrm wayland virtual/egl"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/libwayland-kms.so.*"
FILES_${PN}-dev = " \
    ${libdir}/libwayland-kms.la \
    ${libdir}/libwayland-kms.so \
    ${libdir}/pkgconfig/* \
    ${includedir}/* \
"
FILES_${PN}-staticdev += "${libdir}/libwayland-kms.a"

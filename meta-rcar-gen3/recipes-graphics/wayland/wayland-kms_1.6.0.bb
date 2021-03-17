SUMMARY = "KMS library for Wayland"
LICENSE = "MIT"

LIC_FILES_CHKSUM = " \
    file://wayland-kms.c;beginline=6;endline=24;md5=5cdaac262c876e98e47771f11c7036b5"

PV_append = "+git${SRCREV}"

SRCREV = "15184e5bd3701938a6b30b8f03b471477fc742e8"
SRC_URI = "git://github.com/renesas-rcar/wayland-kms.git;branch=rcar-gen3"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"
S = "${WORKDIR}/git"
DEPENDS = "libdrm wayland gles-user-module wayland-native"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/libwayland-kms.so.*"
FILES_${PN}-dev = " \
    ${libdir}/libwayland-kms.la \
    ${libdir}/libwayland-kms.so \
    ${libdir}/pkgconfig/* \
    ${includedir}/* \
"
FILES_${PN}-staticdev += "${libdir}/libwayland-kms.a"

SUMMARY = "KMS library for Wayland"
LICENSE = "MIT"

LIC_FILES_CHKSUM = " \
    file://wayland-kms.c;beginline=6;endline=24;md5=5cdaac262c876e98e47771f11c7036b5"

PV:append = "+git${SRCREV}"

SRCREV = "a588cecb78acf176ee94d7dfa50a553a1f901efa"
SRC_URI = "git://github.com/renesas-rcar/wayland-kms.git;branch=master;protocol=https"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"
S = "${WORKDIR}/git"
DEPENDS = "libdrm wayland gles-user-module wayland-native"

inherit autotools pkgconfig

FILES:${PN} = "${libdir}/libwayland-kms.so.*"
FILES:${PN}-dev = " \
    ${libdir}/libwayland-kms.la \
    ${libdir}/libwayland-kms.so \
    ${libdir}/pkgconfig/* \
    ${includedir}/* \
"
FILES:${PN}-staticdev += "${libdir}/libwayland-kms.a"

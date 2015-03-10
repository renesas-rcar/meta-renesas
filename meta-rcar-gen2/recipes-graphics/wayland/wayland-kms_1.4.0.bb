SUMMARY = "KMS library for Wayland"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://wayland-kms.c;beginline=6;endline=24;md5=5cdaac262c876e98e47771f11c7036b5"

PV_append = "+git${SRCREV}"

SRCREV = "d0c6d621f610d432abeffe3f4a624760bca61d0d"
SRC_URI = "git://github.com/thayama/wayland-kms;protocol=git;branch=master"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"
S = "${WORKDIR}/git"
DEPENDS = "libdrm wayland gles-user-module"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/libwayland-kms.so.*"
FILES_${PN}-dev = "${libdir}/libwayland-kms.la \
        ${libdir}/libwayland-kms.so ${libdir}/pkgconfig/* \
        ${includedir}/*"
FILES_${PN}-staticdev += "${libdir}/libwayland-kms.a"

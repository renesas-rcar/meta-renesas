SUMMARY = "wayland-wsegl library"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/waylandws.h;beginline=1;endline=22;md5=ebf7ec97b867b0329acbb2c4190fd7a9"

SRC_URI = "git://github.com/renesas-rcar/wayland-wsegl.git;branch=rcar_gen3e"

SRCREV = "d641c05b9e7ea7a8b04d0eaa0815a74a9ea62709"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"

DEPENDS = "libgbm wayland-kms libdrm wayland wayland-native"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

# fixing multiple definition error
SRC_URI:append = " file://0001-wayland-wsegl-fix-multiple-definition-error.patch"

PACKAGES = " \
    ${PN} \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${libdir}/libpvrWAYLAND_WSEGL.so.* \
    ${libdir}/*.so \
"
FILES:${PN}-dbg += "${libdir}/libpvrWAYLAND_WSEGL/.debug/*"
INSANE_SKIP:${PN} += "dev-so"


SUMMARY = "EGL library"
LICENSE = "Apache-2.0 & MIT"
LIC_FILES_CHKSUM = " \
    file://egl.c;beginline=5;endline=15;md5=3677623633a6e459b1f60b1e541c4212 \
"

COMPATIBLE_MACHINE = "r8a7795"

RPROVIDES_${PN} = \
    "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "libEGL.so", "", d)}"
PROVIDES = \
    "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "virtual/egl", "", d)}"

SRCREV = "02b559098042a0aeb9ac63eece547868a140fa46"
SRC_URI = " \
    git://github.com/thayama/libegl;branch=master \
    file://0001-libegl-Remove-duplicate-header-files-of-gles-user-mo.patch \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS = "libgbm gles-user-module"
RDEPENDS_${PN} = "libgbm gles-user-module"

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
    ${PN}-staticdev \
"

FILES_${PN} += "${libdir}/*.so"

INSANE_SKIP_${PN} += "dev-so"

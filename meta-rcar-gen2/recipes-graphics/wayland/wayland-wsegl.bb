DESCRIPTION = "WSEGL library for wayland"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_r8a7790 = "file://r8a7790_wayland_wsegl_library_for_linux.tar.bz2"
SRC_URI_r8a7791 = "file://r8a7791_wayland_wsegl_library_for_linux.tar.bz2"
SRC_URI_r8a7793 = "file://r8a7791_wayland_wsegl_library_for_linux.tar.bz2"
SRC_URI_r8a7794 = "file://r8a7794_wayland_wsegl_library_for_linux.tar.bz2"

DEPENDS = "virtual/mesa wayland-kms libgbm libdrm"

S = "${WORKDIR}/${PN}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}/${libdir}
    cp -rf ${S}/${libdir}/* ${D}/${libdir}/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = "${libdir}/*.so.*"
FILES_${PN}-dev = "${libdir}/*.so ${libdir}/*.la"
FILES_${PN}-staticdev = "${libdir}/*.a"

INSANE_SKIP_${PN} += "ldflags already-stripped"
INSANE_SKIP_${PN}-dev += "ldflags"
INSANE_SKIP_${PN}-staticdev += "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

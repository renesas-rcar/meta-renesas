DESCRIPTION = "Image Renderer module"
LICENSE = "CLOSED"

inherit features_check

REQUIRED_DISTRO_FEATURES = "imr"

SRC_URI = "file://RTM8RC0000ZRRDSS00JPL3E.tar.gz"

COMPATIBLE_MACHINE = "(salvator-x|ebisu|draak|ulcb)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "osal"

S = "${WORKDIR}/drivers/imr"

RCAR_SOC:r8a7795 = "h3"
RCAR_SOC:r8a7796 = "m3"
RCAR_SOC:r8a77965 = "m3n"
RCAR_SOC:r8a77990 = "e3"
RCAR_SOC:r8a77995 = "d3"

EXTRA_OEMAKE = "RCAR_SOC=${RCAR_SOC}"

do_install() {
    install -d ${D}${libdir}
    install -m 644 ${S}/libIMRDRV.so ${D}${libdir}

    install -d ${D}${includedir}/rcar-xos/imr
    install -m 644 ${S}/include/rcar-xos/imr/*.h ${D}${includedir}/rcar-xos/imr
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${libdir}/libIMRDRV.so \
"
FILES:${PN}-dev = " \
    ${includedir}/rcar-xos/imr/*.h \
"
FILES:${PN}-dbg += " \
    ${libdir}/.debug/libIMRDRV.so \
"


DESCRIPTION = "Image Renderer module"
LICENSE = "CLOSED"

SRC_URI = "file://RTM8RC0000ZRRDSS00JPL3E.tar.gz"

COMPATIBLE_MACHINE = "(salvator-x|ulcb)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "osal"

S = "${WORKDIR}/drivers/imr"

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

FILES_${PN} = " \
    ${libdir}/libIMRDRV.so \
"
FILES_${PN}-dev = " \
    ${includedir}/rcar-xos/imr/*.h \
"
FILES_${PN}-dbg += " \
    ${libdir}/.debug/libIMRDRV.so \
"


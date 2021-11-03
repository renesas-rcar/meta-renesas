DESCRIPTION = "OSAL library"
LICENSE = "CLOSED"

inherit features_check

REQUIRED_DISTRO_FEATURES = "osal"

DEPENDS += "kernel-module-cmemdrv"

SRC_URI = "file://RTM8RC0000ZSAL2S00JPL3E.tar.bz2"

S = "${WORKDIR}/os"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec] = "1"

EXTRA_OEMAKE = "INCSHARED=${STAGING_INCDIR}"

do_install() {
    install -d ${D}/${libdir}
    install -d ${D}${includedir}/rcar-xos/osal

    install -m 644 ${S}/libosal_wrapper.a ${D}/${libdir}
    install -m 644 ${S}/osal/include/rcar-xos/osal/*.h ${D}${includedir}/rcar-xos/osal
}


PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dev = " \
    /usr/include/rcar-xos/osal/*.h \
"

FILES_${PN}-staticdev = " \
    /usr/lib/libosal_wrapper.a \
"

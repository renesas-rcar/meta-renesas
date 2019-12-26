DESCRIPTION = "DVD Encryption Library for Linux RCar Gen3"
LICENSE = "CLOSED"

require include/rcar-gen3-path-common.inc

inherit features_check
REQUIRED_DISTRO_FEATURES = "dvd_encryption_library"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

SRC_URI = " \
    file://Software.tar.gz \
"

S = "${WORKDIR}"

includedir = "${RENESAS_DATADIR}/include"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${base_libdir}/optee_armtz
    install -d ${D}${RENESAS_DATADIR}/include
    install -d ${D}${libdir}

    install -m 644 ${S}/TEE/DynamicTA/*.ta \
                   ${D}${base_libdir}/optee_armtz/
    install -m 644 ${S}/TEEC/include/avc_copdvd.h \
                   ${D}${RENESAS_DATADIR}/include/
    install -m 644 ${S}/TEEC/lib/libcopdvd.a \
                   ${D}${libdir}
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = " \
    ${base_libdir}/optee_armtz/*.ta \
"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/include/avc_copdvd.h \
"

FILES_${PN}-staticdev = " \
    ${libdir}/libcopdvd.a \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

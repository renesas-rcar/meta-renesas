DESCRIPTION = "DVD Core-Middleware for Linux for the R-Car Gen3"
LICENSE = "CLOSED"

require include/rcar-gen3-modules-common.inc
require include/dtv-dvd-control.inc

inherit distro_features_check

DEPENDS = " \
    kernel-module-vspmif mmngr-user-module \
    vspmif-user-module kernel-module-vspmif kernel-module-vspm \
    omx-user-module kernel-module-uvcs-drv \
"

PN = "dvd-user-module"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

REQUIRED_DISTRO_FEATURES = "dvd"

SRC_URI_DVD_SW = "file://Software.tar.gz"

SRC_URI = " \
    ${SRC_URI_DVD_SW} \
"

S = "${WORKDIR}"

# do_configure() nothing
do_configure[noexec] = "1"
# do_compile() nothing
do_compile[noexec] = "1"

do_install() {
    # Create destination folders
    install -d ${D}/${libdir}
    install -d ${D}${RENESAS_DATADIR}/include

    # Copy library
    install -m 644 ${S}/${baselib}/*.a ${D}/${libdir}

    # Copy shared header files
    install -m 644 ${S}/include/*.h ${D}${RENESAS_DATADIR}/include
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/include/*.h \
"
FILES_${PN}-staticdev = " \
    ${libdir}/*.a \
"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

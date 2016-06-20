DESCRIPTION = "ISDB-T DTV Software Package for Linux for the R-Car Gen3"
LICENSE = "CLOSED"

require include/rcar-gen3-modules-common.inc
require include/rcar-gen3-dtv-dvd-control.inc

DEPENDS = " \
    kernel-module-vspmif mmngr-user-module \
    vspmif-user-module kernel-module-vspmif kernel-module-vspm \
    omx-user-module kernel-module-uvcs \
    kernel-module-scu-src kernel-module-ssp \
    kernel-module-tddmac kernel-module-tsif \
"

PN = "dtv-user-module"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

REQUIRED_DISTRO_FEATURES = "dtv"

SRC_URI_DTV_SW = "file://Software.tar.gz"
SRC_URI_DTV_UDF = "file://Reference.tar.gz"

SRC_URI = " \
    ${SRC_URI_DTV_SW} \
    ${SRC_URI_DTV_UDF} \
"

S = "${WORKDIR}"

# do_configure() nothing
do_configure[noexec] = "1"
# do_compile() nothing
do_compile[noexec] = "1"

do_install() {
    # Create destination folders
    install -d ${D}/${libdir}
    install -d ${D}/usr/local/include
    install -d ${D}/usr/local/src/dtv/reference

    # Copy library
    install -m 644 ${S}/${baselib}/*.a ${D}/${libdir}/

    # Copy shared header files
    install -m 644 ${S}/include/*.h ${D}/usr/local/include

    # Copy reference files
    install -m 644 ${S}/userfunc/*.h ${D}/usr/local/src/dtv/reference
    install -m 644 ${S}/userfunc/*.c ${D}/usr/local/src/dtv/reference
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dev = " \
    /usr/local/include/*.h \
    /usr/local/src/dtv/reference/* \
"
FILES_${PN}-staticdev = " \
    ${libdir}/*.a \
"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
PN = "dtv"
PR = "r0"
SRC_URI = "file://dtv.tar.bz2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_populate_lic[noexec] = "1"

do_install() {
    # Create share folders
    mkdir -p ${D}/usr/local/include/ ${D}/usr/local/lib ${D}/usr/local/src/dtv/reference

    # Copy share files to destination
    cp -f ${WORKDIR}/dtv/include/*.h ${D}/usr/local/include/
    cp -f ${WORKDIR}/dtv/lib/libdtv.a ${D}/usr/local/lib
    cp -f ${WORKDIR}/dtv/userfunc/* ${D}/usr/local/src/dtv/reference
}

SYSROOT_PREPROCESS_FUNCS += "do_populate_reference_src"

do_populate_reference_src () {
	sysroot_stage_dir ${D}/usr/local ${SYSROOT_DESTDIR}/usr/local
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dev = " \
    /usr/local/include/*.h \
    /usr/local/src/dtv/reference/*.c \
    /usr/local/src/dtv/reference/*.h \
"

FILES_${PN}-staticdev = " \
    /usr/local/lib/*.a \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"

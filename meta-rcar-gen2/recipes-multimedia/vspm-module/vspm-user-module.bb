require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "vspm-kernel-module"
PN = "vspm-user-module"
PR = "r0"
SRC_URI = "file://vspm-user.tar.bz2"

S = "${WORKDIR}"

do_compile() {
    # Build shared library
    cd ${S}/vspm/if
    rm -rf ${S}/vspm/if/libvspm.so*
    make all ARCH=arm
    # Copy shared library for reference from other modules
    cp -P ${S}/vspm/if/libvspm.so* ${LIBSHARED}
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/usr/local/lib/ ${D}/usr/local/include
    # Copy shared library
    cp -P ${S}/vspm/if/libvspm.so* ${D}/usr/local/lib/
    # Copy shared header files
    cp -f ${BUILDDIR}/include/vspm_public.h ${D}/usr/local/include
    cp -f ${BUILDDIR}/include/vsp_drv.h ${D}/usr/local/include
    cp -f ${BUILDDIR}/include/tddmac_drv.h ${D}/usr/local/include
}

do_clean_source() {
    rm -f ${LIBSHARED}/libvspm.so*
    rm -f ${BUILDDIR}/include/vspm_public.h
    rm -f ${BUILDDIR}/include/vsp_drv.h
    rm -f ${BUILDDIR}/include/tddmac_drv.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /usr/local/lib/libvspm.so.* \
"

FILES_${PN}-dev = " \
    /usr/local/lib \
    /usr/local/lib/libvspm.so \
    /usr/local/lib/* \
    /usr/local/include \
    /usr/local/include/*.h \
"

RPROVIDES_${PN} += "vspm-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"

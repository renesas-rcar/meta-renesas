require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "mmngrbuf-kernel-module"
PN = "mmngrbuf-user-module"
PR = "r0"
S = "${WORKDIR}/mmngrbuf"
SRC_URI = "file://mmngrbuf.tar.bz2"


do_compile() {
    # Build shared library
    cd ${S}/if
    rm -rf ${S}/if/libmmngrbuf.so*
    make all ARCH=arm
    # Copy shared library into shared folder
    cp -P ${S}/if/libmmngrbuf.so* ${LIBSHARED}
}

do_install() {
    mkdir -p ${D}/usr/local/lib/ ${D}/usr/local/include

    # Copy shared library
    cp -P ${S}/if/libmmngrbuf.so* ${D}/usr/local/lib/
    cd ${D}/usr/local/lib/
    # Copy shared header files
    cp -f ${BUILDDIR}/include/mmngr_buf_user_public.h ${D}/usr/local/include
    cp -f ${BUILDDIR}/include/mmngr_buf_user_private.h ${D}/usr/local/include
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${LIBSHARED}/libmmngrbuf.so*
    rm -Rf ${BUILDDIR}/include/mmngr_buf_user_public.h
    rm -Rf ${BUILDDIR}/include/mmngr_buf_user_private.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /usr/local/lib/libmmngrbuf.so.* \
"

FILES_${PN}-dev = " \
    /usr/local/include \
    /usr/local/include/*.h \
    /usr/local/lib \
    /usr/local/lib/libmmngrbuf.so \
"

RPROVIDES_${PN} += "mmngrbuf-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

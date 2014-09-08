require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "s3ctl-kernel-module"
PN = "s3ctl-user-module"
PR = "r0"
SRC_URI = "file://s3ctl.tar.bz2"

S = "${WORKDIR}/s3ctl"

do_compile() {
    # Build shared library
    cd ${S}/if
    rm -rf libs3ctl.so*
    make all ARCH=arm
    # Copy shared library for reference from other modules
    cp -P ${S}/if/libs3ctl.so* ${LIBSHARED}
}

do_install() {
    # Create shared folder
    mkdir -p ${D}/usr/local/lib/ ${D}/usr/local/include/
    # Copy share library
    cp -P ${S}/if/libs3ctl.so* ${D}/usr/local/lib/
    # Copy shared header files
    cp -f ${BUILDDIR}/include/s3ctl_user_public.h ${D}/usr/local/include/
    cp -f ${BUILDDIR}/include/s3ctl_user_private.h ${D}/usr/local/include/
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${LIBSHARED}/libs3ctl.so*
    rm -f ${BUILDDIR}/include/s3ctl_user_public.h
    rm -f ${BUILDDIR}/include/s3ctl_user_private.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /usr/local/lib/libs3ctl.so.* \
"

FILES_${PN}-dev = " \
    /usr/local/lib/libs3ctl.so \
    /usr/local/include/*.h \
"

RPROVIDES_${PN} += "s3ctl-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

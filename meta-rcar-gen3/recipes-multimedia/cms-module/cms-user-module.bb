DESCRIPTION = "R-Car Gen3 Color Management System"
LICENSE = "CLOSED"

require include/cms-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_CMSBCM = "${@bb.utils.contains('USE_CMSBCM', '1', 'file://RTM8RC0000ZVC1LQ00JPL3E.tar.gz;subdir=cms/bcm', '', d)}"
SRC_CMSBLC = "${@bb.utils.contains('USE_CMSBLC', '1', 'file://RTM8RC0000ZVC3LQ00JPL3E.tar.gz;subdir=cms/blc', '', d)}"
SRC_CMSDGC = "${@bb.utils.contains('USE_CMSDGC', '1', 'file://RTM8RC0000ZVC2LQ00JPL3E.tar.gz;subdir=cms/dgc', '', d)}"

SRC_URI = " \
    ${SRC_CMSBCM} \
    ${SRC_CMSBLC} \
    ${SRC_CMSDGC} \
"

S = "${WORKDIR}/${BPN}-${PV}"
UNPACKDIR = "${S}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Create the lib directory
    if [ "X${USE_CMS}" = "X1" ]; then
        install -d ${D}/${libdir}
    fi

    if [ "X${USE_CMSBCM}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/bcm
        install -m 755 ${S}/cms/bcm/${baselib}/libcmsbcm.so.3.0.2 ${D}/${libdir}
        install -m 644 ${S}/cms/bcm/include/*.h ${D}/${includedir}/cms/bcm

        cd ${D}/${libdir}
        ln -s libcmsbcm.so.3.0.2 libcmsbcm.so.1
        ln -s libcmsbcm.so.1 libcmsbcm.so
    fi

    if [ "X${USE_CMSBLC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/blc
        install -m 755 ${S}/cms/blc/${baselib}/libcmsblc.so.3.0.2 ${D}/${libdir}
        install -m 644 ${S}/cms/blc/include/*.h ${D}/${includedir}/cms/blc

        cd ${D}/${libdir}
        ln -s libcmsblc.so.3.0.2 libcmsblc.so.1
        ln -s libcmsblc.so.1 libcmsblc.so
    fi

    if [ "X${USE_CMSDGC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/dgc
        install -m 755 ${S}/cms/dgc/${baselib}/libcmsdgc.so.3.0.2 ${D}/${libdir}
        install -m 644 ${S}/cms/dgc/include/*.h ${D}/${includedir}/cms/dgc

        cd ${D}/${libdir}
        ln -s libcmsdgc.so.3.0.2 libcmsdgc.so.1
        ln -s libcmsdgc.so.1 libcmsdgc.so
    fi
}

FILES:${PN} = " \
    ${libdir}/*.so.* \
    ${libdir}/lib*.so \
"

FILES:${PN}-dev = " \
    ${includedir} \
"

INSANE_SKIP:${PN} = "dev-so"

#To avoid already-stripped errors and not stripped libs from packages
INSANE_SKIP:${PN} += "already-stripped"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

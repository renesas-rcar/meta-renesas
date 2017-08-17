DESCRIPTION = "R-Car Gen3 Color Management System"
LICENSE = "CLOSED"

require include/cms-control.inc

SRC_CMSBCM = "${@bb.utils.contains('USE_CMSBCM', '1', 'file://RTM0AC0000JRCMBCV0SL40C.tar.gz;subdir=cms/bcm', '', d)}"
SRC_CMSBLC = "${@bb.utils.contains('USE_CMSBLC', '1', 'file://RTM0AC0000JRCMBLC0SL40C.tar.gz;subdir=cms/blc', '', d)}"
SRC_CMSDGC = "${@bb.utils.contains('USE_CMSDGC', '1', 'file://RTM0AC0000JRCMDGV0SL40C.tar.gz;subdir=cms/dgc', '', d)}"

SRC_URI = " \
    ${SRC_CMSBCM} \
    ${SRC_CMSBLC} \
    ${SRC_CMSDGC} \
"

S = "${WORKDIR}/cms"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # Create the lib directory
    install -d ${D}/${libdir}

    if [ "X${USE_CMSBCM}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/bcm
        install -m 755 ${S}/bcm/${baselib}/libcmsbcm.so.1.0.0 ${D}/${libdir}
        install -m 644 ${S}/bcm/include/*.h ${D}/${includedir}/cms/bcm

        cd ${D}/${libdir}
        ln -s libcmsbcm.so.1.0.0 libcmsbcm.so.1
        ln -s libcmsbcm.so.1 libcmsbcm.so
    fi

    if [ "X${USE_CMSBLC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/blc
        install -m 755 ${S}/blc/${baselib}/libcmsblc.so.1.0.0 ${D}/${libdir}
        install -m 644 ${S}/blc/include/*.h ${D}/${includedir}/cms/blc

        cd ${D}/${libdir}
        ln -s libcmsblc.so.1.0.0 libcmsblc.so.1
        ln -s libcmsblc.so.1 libcmsblc.so
    fi

    if [ "X${USE_CMSDGC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/dgc
        install -m 755 ${S}/dgc/${baselib}/libcmsdgc.so.1.0.0 ${D}/${libdir}
        install -m 644 ${S}/dgc/include/*.h ${D}/${includedir}/cms/dgc

        cd ${D}/${libdir}
        ln -s libcmsdgc.so.1.0.0 libcmsdgc.so.1
        ln -s libcmsdgc.so.1 libcmsdgc.so
    fi
}

FILES_${PN} = " \
    ${libdir}/*.so.* \
    ${libdir}/lib*.so \
"

FILES_${PN}-dev = " \
    ${includedir} \
"

INSANE_SKIP_${PN} = "dev-so"

# Skip debug strip of do_populate_sysroot()
INHIBIT_SYSROOT_STRIP = "1"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

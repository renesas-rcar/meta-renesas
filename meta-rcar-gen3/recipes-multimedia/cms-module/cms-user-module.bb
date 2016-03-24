DESCRIPTION = "R-Car Gen3 Color Management System"
LICENSE = "CLOSED"

require include/cms-options.inc

SRC_CMSBCM = "${@base_contains('USE_CMSBCM', '1', 'file://RTM0AC0000JRCMBCV0SL40C.tar.gz;subdir=cms/bcm', '', d)}"
SRC_CMSBLC = "${@base_contains('USE_CMSBLC', '1', 'file://RTM0AC0000JRCMBLC0SL40C.tar.gz;subdir=cms/blc', '', d)}"
SRC_CMSDGC = "${@base_contains('USE_CMSDGC', '1', 'file://RTM0AC0000JRCMDGV0SL40C.tar.gz;subdir=cms/dgc', '', d)}"

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
    if [ "X${USE_CMSBCM}" = "X1" -o "X${USE_CMSBLC}" = "X1" -o "X${USE_CMSDGC}" = "X1" ]; then
        install -d ${D}/${libdir}
    fi

    if [ "X${USE_CMSBCM}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/bcm
        install -m 755 ${S}/bcm/${baselib}/libcmsbcm.so.0.0.1 ${D}/${libdir}
        install -m 644 ${S}/bcm/include/*.h ${D}/${includedir}/cms/bcm

        cd ${D}/${libdir}
        ln -s libcmsbcm.so.0.0.1 libcmsbcm.so.0
        ln -s libcmsbcm.so.0 libcmsbcm.so
    fi

    if [ "X${USE_CMSBLC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/blc
        install -m 755 ${S}/blc/${baselib}/libcmsblc.so.0.0.1 ${D}/${libdir}
        install -m 644 ${S}/blc/include/*.h ${D}/${includedir}/cms/blc

        cd ${D}/${libdir}
        ln -s libcmsblc.so.0.0.1 libcmsblc.so.0
        ln -s libcmsblc.so.0 libcmsblc.so
    fi

    if [ "X${USE_CMSDGC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/dgc
        install -m 755 ${S}/dgc/${baselib}/libcmsdgc.so.0.0.1 ${D}/${libdir}
        install -m 644 ${S}/dgc/include/*.h ${D}/${includedir}/cms/dgc

        cd ${D}/${libdir}
        ln -s libcmsdgc.so.0.0.1 libcmsdgc.so.0
        ln -s libcmsdgc.so.0 libcmsdgc.so
    fi
}

FILES_${PN} = " \
    ${libdir}/*.so.* \
    ${libdir}/lib*.so"

INSANE_SKIP_${PN} += "dev-so"

FILES_${PN}-dev = " \
    ${includedir}"

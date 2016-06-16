DESCRIPTION = "R-Car Gen3 Color Management System"
LICENSE = "CLOSED"

require include/rcar-gen3-cms-options.inc

SRC_CMSBCMV = "${@base_contains('USE_CMSBCMV', '1', 'file://RTM0AC0000JRCMBCV0SL40C.tar.gz;subdir=cms/bcmv', '', d)}"
SRC_CMSBCMC = "${@base_contains('USE_CMSBCMC', '1', 'file://RTM0AC0000JRCMBCC0SL40C.tar.gz;subdir=cms/bcmc', '', d)}"
SRC_CMSBLC = "${@base_contains('USE_CMSBLC', '1', 'file://RTM0AC0000JRCMBLC0SL40C.tar.gz;subdir=cms/blc', '', d)}"
SRC_CMSDGC = "${@base_contains('USE_CMSDGC', '1', 'file://RTM0AC0000JRCMDGV0SL40C.tar.gz;subdir=cms/dgc', '', d)}"

SRC_URI = " \
    ${SRC_CMSBCMV} \
    ${SRC_CMSBCMC} \
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

    if [ "X${USE_CMSBCMV}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/bcmv
        install -m 755 ${S}/bcmv/${baselib}/libcmsbcmv.so.0.4.0 ${D}/${libdir}
        install -m 644 ${S}/bcmv/include/*.h ${D}/${includedir}/cms/bcmv

        cd ${D}/${libdir}
        ln -s libcmsbcmv.so.0.4.0 libcmsbcmv.so.0
        ln -s libcmsbcmv.so.0 libcmsbcmv.so
    fi

    if [ "X${USE_CMSBCMC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/bcmc
        install -m 755 ${S}/bcmc/${baselib}/libcmsbcmc.so.0.4.0 ${D}/${libdir}
        install -m 644 ${S}/bcmc/include/*.h ${D}/${includedir}/cms/bcmc

        cd ${D}/${libdir}
        ln -s libcmsbcmc.so.0.4.0 libcmsbcmc.so.0
        ln -s libcmsbcmc.so.0 libcmsbcmc.so
    fi

    if [ "X${USE_CMSBLC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/blc
        install -m 755 ${S}/blc/${baselib}/libcmsblc.so.0.4.0 ${D}/${libdir}
        install -m 644 ${S}/blc/include/*.h ${D}/${includedir}/cms/blc

        cd ${D}/${libdir}
        ln -s libcmsblc.so.0.4.0 libcmsblc.so.0
        ln -s libcmsblc.so.0 libcmsblc.so
    fi

    if [ "X${USE_CMSDGC}" = "X1" ]; then
        install -d ${D}/${includedir}/cms/dgc
        install -m 755 ${S}/dgc/${baselib}/libcmsdgc.so.0.4.0 ${D}/${libdir}
        install -m 644 ${S}/dgc/include/*.h ${D}/${includedir}/cms/dgc

        cd ${D}/${libdir}
        ln -s libcmsdgc.so.0.4.0 libcmsdgc.so.0
        ln -s libcmsdgc.so.0 libcmsdgc.so
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

DESCRIPTION = "Memory Manager Kernel module for Renesas R-Car Gen3"

require mmngr_drv.inc
require include/dtv-dvd-control.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngr"
PR = "r0"

S = "${WORKDIR}/git"
MMNGR_DRV_DIR = "mmngr_drv/mmngr/mmngr-module/files/mmngr"

MMNGR_CFG_salvator-x = "MMNGR_SALVATORX"
MMNGR_CFG_h3ulcb = "MMNGR_SALVATORX"
MMNGR_CFG_m3ulcb = "MMNGR_SALVATORX"
MMNGR_CFG_m3nulcb = "MMNGR_SALVATORX"
MMNGR_CFG_ebisu = "MMNGR_EBISU"

includedir = "${RENESAS_DATADIR}/include"
SSTATE_DUPWHITELIST += "${STAGING_INCDIR}"

# Build Memory Manager kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    export MMNGR_CONFIG=${MMNGR_CFG}

    if [ "X${USE_DTV}" = "X1" ]; then
        export MMNGR_SSP_CONFIG="MMNGR_SSP_ENABLE"
    else
        export MMNGR_SSP_CONFIG="MMNGR_SSP_DISABLE"
    fi

    export MMNGR_IPMMU_MMU_CONFIG="IPMMU_MMU_DISABLE"

    cd ${S}/${MMNGR_DRV_DIR}/drv
    install -d ${INCSHARED}
    make all
}

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${KERNELSRC}/include
    install -d ${D}/${includedir}

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${MMNGR_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/mmngr.symvers

    # Install kernel module
    install -m 644 ${S}/${MMNGR_DRV_DIR}/drv/mmngr.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${MMNGR_DRV_DIR}/include/mmngr_public.h ${KERNELSRC}/include/
    install -m 644 ${S}/${MMNGR_DRV_DIR}/include/mmngr_private.h ${KERNELSRC}/include/
    install -m 644 ${S}/${MMNGR_DRV_DIR}/include/mmngr_public_cmn.h ${KERNELSRC}/include/
    install -m 644 ${S}/${MMNGR_DRV_DIR}/include/mmngr_private_cmn.h ${KERNELSRC}/include/

    # Install shared header file to ${includedir}
    install -m 644 ${S}/${MMNGR_DRV_DIR}/include/mmngr_public_cmn.h ${D}/${includedir}/
    install -m 644 ${S}/${MMNGR_DRV_DIR}/include/mmngr_private_cmn.h ${D}/${includedir}/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/mmngr.ko \
"

RPROVIDES_${PN} += "kernel-module-mmngr"

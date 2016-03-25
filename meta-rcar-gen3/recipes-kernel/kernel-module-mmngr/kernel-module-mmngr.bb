DESCRIPTION = "Memory Manager Kernel module for Renesas R-Car Gen3"

require mmngr_drv.inc
require include/dtv-dvd-control.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngr"
PR = "r0"

S = "${WORKDIR}/git"
MMNGR_DRV_DIR = "mmngr_drv/mmngr/mmngr-module/files/mmngr"

MMNGR_CFG_salvator-x = "MMNGR_SALVATORX"

do_compile() {
    export MMNGR_CONFIG=${MMNGR_CFG}

    if [ "X${USE_DTV}" = "X1" ]; then
        export MMNGR_SSP_CONFIG="MMNGR_SSP_ENABLE"
    else
        export MMNGR_SSP_CONFIG="MMNGR_SSP_DISABLE"
    fi

    cd ${S}/${MMNGR_DRV_DIR}/drv
    make all
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/src/kernel/include

    # Copy shared library for reference from other modules
    install -m 644 ${S}/${MMNGR_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/mmngr.symvers
    install -m 644 ${S}/${MMNGR_DRV_DIR}/drv/Module.symvers ${D}/usr/src/kernel/include/mmngr.symvers

    # Copy kernel module
    install -m 644 ${S}/${MMNGR_DRV_DIR}/drv/mmngr.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Copy shared header files
    install -m 644 ${KERNELSRC}/include/mmngr_public.h ${D}/usr/src/kernel/include
    install -m 644 ${KERNELSRC}/include/mmngr_private.h ${D}/usr/src/kernel/include
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/mmngr.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include/mmngr.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "kernel-module-mmngr"

do_configure[noexec] = "1"

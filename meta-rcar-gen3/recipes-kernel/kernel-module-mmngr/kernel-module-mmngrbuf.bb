DESCRIPTION = "Memory Manager Buffer Kernel module for Renesas R-Car Gen3"

require mmngr_drv.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngrbuf"
PR = "r0"

S = "${WORKDIR}/git"
MMNGRBUF_DRV_DIR = "mmngr_drv/mmngrbuf/mmngrbuf-module/files/mmngrbuf"

includedir="${RENESAS_DATADIR}/include"
SSTATE_DUPWHITELIST += "${STAGING_INCDIR}"

do_compile() {
    cd ${S}/${MMNGRBUF_DRV_DIR}/drv
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
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/mmngrbuf.symvers

    # Install kernel module
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/drv/mmngrbuf.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/include/mmngr_buf_private.h ${KERNELSRC}/include/
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/include/mmngr_buf_private_cmn.h ${KERNELSRC}/include/

    # Install shared header files to ${includedir}
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/include/mmngr_buf_private_cmn.h ${D}/${includedir}/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/mmngrbuf.ko \
"

RPROVIDES_${PN} += "kernel-module-mmngrbuf"

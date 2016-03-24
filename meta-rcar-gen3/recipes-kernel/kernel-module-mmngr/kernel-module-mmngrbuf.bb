DESCRIPTION = "Memory Manager Buffer Kernel module for Renesas R-Car Gen3"

require mmngr_drv.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngrbuf"
PR = "r0"

S = "${WORKDIR}/git"
MMNGRBUF_DRV_DIR = "mmngr_drv/mmngrbuf/mmngrbuf-module/files/mmngrbuf"

do_compile() {
    cd ${S}/${MMNGRBUF_DRV_DIR}/drv
    make all
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/src/kernel/include

    # Copy shared library for reference from other modules
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/mmngrbuf.symvers
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/drv/Module.symvers ${D}/usr/src/kernel/include/mmngrbuf.symvers

    # Copy kernel module
    install -m 644 ${S}/${MMNGRBUF_DRV_DIR}/drv/mmngrbuf.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/mmngrbuf.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include/mmngrbuf.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "kernel-module-mmngrbuf"

do_configure[noexec] = "1"

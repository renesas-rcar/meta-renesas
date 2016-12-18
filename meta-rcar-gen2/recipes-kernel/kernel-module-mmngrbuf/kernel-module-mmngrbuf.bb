DESCRIPTION = "Memory Manager Buffer Kernel module for Renesas R-Car Gen2"

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://drv/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://drv/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
    file://include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

require include/rcar-gen2-modules-common.inc

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngrbuf"
SRC_URI = "file://mmngrbuf.tar.bz2 \
	   file://0001-Edit-dma_buf_export-with-new-prototype.patch \
           file://0002-change-in-dma_buf_ops-function-signature.patch \
"

S = "${WORKDIR}/mmngrbuf"

do_compile() {
    cd ${S}/drv
    make all ARCH=arm
}

do_install () {
    # Create destination folders
    install -d  ${D}/lib/modules/${KERNEL_VERSION}/extra/ 
    install -d ${KERNELSRC}/include


    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    cp -f ${S}/drv/Module.symvers ${KERNELSRC}/include/mmngrbuf.symvers

    # Copy kernel module
    cp -f ${S}/drv/mmngrbuf.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

RPROVIDES_${PN} += "kernel-module-mmngrbuf"
do_configure[noexec] = "1"

KERNEL_MODULE_AUTOLOAD = "mmngrbuf"


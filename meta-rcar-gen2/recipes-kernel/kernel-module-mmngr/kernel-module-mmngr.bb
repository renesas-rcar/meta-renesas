DESCRIPTION = "Memory Manager Kernel module for Renesas R-Car Gen2"

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
PN = "kernel-module-mmngr"
SRC_URI = "file://mmngr.tar.bz2"

S = "${WORKDIR}/mmngr"

MMNGR_CFG_r8a7792 = "MMNGR_ALT"

do_compile() {
    export MMNGR_CONFIG=${MMNGR_CFG}

    if [ "X${USE_DTV}" = "X1" ]; then
        export MMNGR_SSP_CONFIG="MMNGR_SSP_ENABLE"
    else
        export MMNGR_SSP_CONFIG="MMNGR_SSP_DISABLE"
    fi
    cd ${S}/drv
    make all ARCH=arm
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${KERNELSRC}/include

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/drv/Module.symvers ${KERNELSRC}/include/mmngr.symvers

    # Copy kernel module
    install  ${S}/drv/mmngr.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/include/mmngr_public.h ${KERNELSRC}/include/
    install -m 644 ${S}/include/mmngr_private.h ${KERNELSRC}/include/
}

PACKAGES = "\
    ${PN} \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/mmngr.ko \
"

RPROVIDES_${PN} += "kernel-module-mmngr"

KERNEL_MODULE_AUTOLOAD = "mmngr"

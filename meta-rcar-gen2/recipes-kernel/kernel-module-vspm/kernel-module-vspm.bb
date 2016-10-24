DESCRIPTION = "VSP Manager for the R-Car Gen2"

require include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://vspm/drv/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://vspm/drv/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-vspm"
PR = "r0"

SRC_URI = "file://vspm-kernel.tar.bz2 \
	   file://add-wno-error-day-time.patch \
	   file://0001-rename-clks-vspm.patch \
	   file://0002-vspm-Update-from-kernel-3.10-to-kernel-4.4.patch \
	   file://0003-VSPM-add-device-register-method.patch \
	   file://0004-modify-initialize-deinitilize-vspm-sub-modules.patch \
	   file://0005-disable-ttdmac-support.patch \
	   file://0007-V2H-remove-unavailable-channels.patch \
"


S = "${WORKDIR}"

VSPM_CFG_r8a7792 = "M2CONFIG"

VSPM_DRV_DIR = "vspm/drv"
VSPM_INCLUDE_DIR = "vspm/include"

do_compile() {
    export VSPM_CONFIG=${VSPM_CFG}
    cd ${S}/${VSPM_DRV_DIR}
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/ 
    install -d ${KERNELSRC}/include
    install -d ${D}/usr/include

    # Install kernel module
    install -m 644 ${S}/${VSPM_DRV_DIR}/vspm.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${VSPM_INCLUDE_DIR}/vspm_public.h ${KERNELSRC}/include/
    install -m 644 ${S}/${VSPM_INCLUDE_DIR}/vsp_drv.h ${KERNELSRC}/include/
    install -m 644 ${S}/${VSPM_INCLUDE_DIR}/tddmac_drv.h ${KERNELSRC}/include/
    install -m 644 ${S}/${VSPM_INCLUDE_DIR}/vspm_if.h ${KERNELSRC}/include/

    install -m 644 ${S}/${VSPM_DRV_DIR}/Module.symvers ${KERNELSRC}/include/vspm.symvers

    # Install shared header file
    install -m 644 ${S}/${VSPM_INCLUDE_DIR}/vspm_if.h ${D}/usr/include/
}


PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vspm.ko \
"
FILES_${PN}-dev = " \
    /usr/include/*.h \
"
RPROVIDES_${PN} += "kernel-module-vspm"

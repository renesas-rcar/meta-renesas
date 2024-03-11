DESCRIPTION = "Linux kernel for the R-Car V3x/V4x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "x5h"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
    file://0001-arm64-dts-renesas-r8a779g0-Add-Native-device-support.patch"

LINUX_VERSION ?= "5.10.147"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

PACKAGES += "${PN}-uapi"

do_install_append_rcar-gen5() {
    # Install R-Car specific UAPI headers
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/

    # Install dmatest module
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    mv ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}
# Deploy vmlinux to deploy directory
do_deploy_append_rcar-gen5() {
    install -m 0644 ${KERNEL_OUTPUT_DIR}/vmlinux $deployDir/
}

FILES_${PN}-uapi = "/usr/include"

# uio_pdrv_genirq and dmatest configuration
KERNEL_MODULE_AUTOLOAD_append = " uio_pdrv_genirq dmatest"
KERNEL_MODULE_PROBECONF_append = " uio_pdrv_genirq dmatest"
module_conf_uio_pdrv_genirq_append = ' options uio_pdrv_genirq of_id="generic-uio"'


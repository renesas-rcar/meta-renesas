DESCRIPTION = "Linux kernel for the R-Car Gateway based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "spider"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.41/rcar-5.1.6.rc3"
SRCREV = "2f11ac9c63f737ebbd3a973ba3396e4135e5ae5d"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "5.10.41"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

# uio_pdrv_genirq configuration
KERNEL_MODULE_AUTOLOAD_append = " uio_pdrv_genirq"
KERNEL_MODULE_PROBECONF_append = " uio_pdrv_genirq"
module_conf_uio_pdrv_genirq_append = ' options uio_pdrv_genirq of_id="generic-uio"'

PACKAGES += "${PN}-uapi"

# Install S4 specific UAPI headers
do_install_append() {
    install -d ${D}/usr/include/linux/
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/
    mv ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

FILES_${PN}-uapi = "/usr/include"
# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD_append = " dmatest"
KERNEL_MODULE_PROBECONF_append = " dmatest"

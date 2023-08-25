DESCRIPTION = "Linux kernel for the R-Car Gateway based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "(spider|s4sk)"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.41/rcar-5.1.7.rc11.1"
SRCREV = "bcde3c5f9fe805e0086ca0af5cd1c055b290f950"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH};protocol=https \
    file://0001-arm64-dts-renesas-r8a779f0-Add-Native-device-support.patch \
    file://0002-arm64-dts-renesas-r8a779f0-Enable-IPMMU-main-and-HC-.patch \
    file://0003-arm64-dts-renesas-r8a779f0-Enable-IPMMU-for-PCIe0-1.patch \
    file://0004-arm64-dts-renesas-r8a779f0-Enable-IPMMU-for-eMMC.patch \
    "
SRC_URI += " \
    file://ufs.cfg \
    file://r8a779f0_ufs.bin \
"

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

do_download_firmware () {
    install -d ${STAGING_KERNEL_DIR}/firmware
    install -m 755 ${WORKDIR}/r8a779f0_ufs.bin ${STAGING_KERNEL_DIR}/firmware/
}

addtask do_download_firmware after do_configure before do_compile

# Install S4 specific UAPI headers and ufs firmware
do_install_append() {
    install -d ${D}/usr/include/linux/
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/lib/firmware/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/
    mv ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${S}/firmware/r8a779f0_ufs.bin ${D}/lib/firmware/
}

FILES_${PN}-uapi = "/usr/include"
# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD_append = " dmatest"
KERNEL_MODULE_PROBECONF_append = " dmatest"

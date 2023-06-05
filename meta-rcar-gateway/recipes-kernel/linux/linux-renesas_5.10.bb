DESCRIPTION = "Linux kernel for the R-Car Gateway based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "spider|s4sk-proto"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.41/rcar-5.1.7.rc6"
SRCREV = "9f7244d31fd1afce9854f572ed96fe4b66bf0e52"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH};protocol=https"

SRC_URI:append = " \
    file://0001-arm64-dts-renesas-Add-Renesas-R8A779F0-Starter-Kit-p.patch \
    file://0002-arm64-dts-renesas-r8a779f0-s4sk-prototype-Add-PCIe-d.patch \
    file://0003-arm64-dts-renesas-r8a779f0-s4sk-prototype-Enable-UFS.patch \
    file://0004-arm64-dts-renesas-r8a779f0-s4sk-prototype-Add-rswitc.patch \
    file://0005-net-ethernet-renesas-rswitch-Add-support-USXGMII-mod.patch \
    file://0006-net-ethernet-renesas-rswich-Fix-issue-cannot-use-bot.patch \
    file://0001-arm64-dts-renesas-r8a779f0-s4sk-prototype-Change-DDR.patch \
    file://pcie_ep.cfg \
    file://pci_ep_test.cfg \
"

LINUX_VERSION ?= "5.10.41"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

# uio_pdrv_genirq configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'

PACKAGES += "${PN}-uapi"

# Install S4 specific UAPI headers
do_install:append() {
    install -d ${D}/usr/include/linux/
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/
    mv ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

FILES:${PN}-uapi = "/usr/include"
# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD:append = " dmatest"
KERNEL_MODULE_PROBECONF:append = " dmatest"

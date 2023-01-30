DESCRIPTION = "Linux kernel for the R-Car V3x/V4x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk)"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
    file://0001-arm64-dts-renesas-r8a779g0-Add-Native-device-support.patch"

LINUX_VERSION ?= "5.10.147"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

# uio_pdrv_genirq configuration
KERNEL_MODULE_AUTOLOAD_append = " uio_pdrv_genirq"
KERNEL_MODULE_PROBECONF_append = " uio_pdrv_genirq"
module_conf_uio_pdrv_genirq_append = ' options uio_pdrv_genirq of_id="generic-uio"'


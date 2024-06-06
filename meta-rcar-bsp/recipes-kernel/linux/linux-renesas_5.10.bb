DESCRIPTION = "Linux kernel for the R-Car V3x/V4x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|grayhawk)"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
    file://0001-arm64-dts-renesas-r8a779g0-Add-Native-device-support.patch \
    file://pid_in_contextidr.cfg \
"

# Work-around to fix perf build error
SRC_URI:append = " file://init_disassemble_info-signature-changes-causes-compile-failures.patch"

LINUX_VERSION ?= "5.10.147"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

# uio_pdrv_genirq configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'

do_src_package_preprocess () {
        # Trim build paths from comments in generated sources to ensure reproducibility
        sed -i -e "s,${S}/,,g" \
               -e "s,${B}/,,g" \
            ${B}/drivers/video/logo/logo_linux_clut224.c \
            ${B}/drivers/tty/vt/consolemap_deftbl.c \
            ${B}/lib/oid_registry_data.c
}
addtask do_src_package_preprocess after do_compile before do_install

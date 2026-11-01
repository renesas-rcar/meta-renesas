DESCRIPTION = "Linux kernel for the R-Car V3x/V4x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "(falcon|condor|eagle|whitehawk|grayhawk)"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
    file://0001-arm64-dts-renesas-r8a779g0-Add-Native-device-support.patch \
    file://0002-arm64-dts-renesas-r8a779g-0-3-4-5-du-native-Add-DU-N.patch \
    file://0003-arm64-dts-renesas-r8a779g-0-3-4-5-native-rgid-Add-Na.patch \
    file://pid_in_contextidr.cfg \
"
SRC_URI:append:rcar-v4x = "${@oe.utils.conditional("RGID_ON", "1", " file://rcar_rgid.cfg", "", d )}"

# Work-around to fix perf build error
SRC_URI:append = " file://init_disassemble_info-signature-changes-causes-compile-failures.patch"

# LPM support for V4M (LPM_ON = "1")
SUPPORT_LPM = " \
    file://0001-arm64-dts-r8a779h0-Fix-CPU-cache-hierarchy-to-enable.patch \
"

# LPM device tree changes when RGID is disabled
SUPPORT_LPM_RGID_OFF = " \
    file://0002-arm64-dts-renesas-gray-hawk-Add-reserved-area-for-Li.patch \
    file://0003-arm64-dts-renesas-gray-hawk-Disable-TMU-to-avoid-ear.patch \
"

# LPM device tree changes when RGID is enabled (RGID_ON = "1")
SUPPORT_LPM_RGID_ON = " \
    file://0001-arm64-dts-renesas-gray-hawk-rgid-Disable-TMU-to-avoi.patch \
    file://0002-arm64-dts-renesas-gray-hawk-rgid-Add-reserved-areas-.patch \
"

SUPPORT_LPM += "${@oe.utils.conditional('RGID_ON', '1', d.getVar('SUPPORT_LPM_RGID_ON'), d.getVar('SUPPORT_LPM_RGID_OFF'), d)}"

SRC_URI:append:r8a779h0 = " ${@oe.utils.conditional("LPM_ON", "1", "${SUPPORT_LPM}", "", d )}"

LINUX_VERSION ?= "5.10.235"
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

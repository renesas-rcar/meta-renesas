SOC_FAMILY =. "rcar-gen5-evb:"

DEFAULTTUNE ?= "aarch64"
require conf/machine/include/arm/arch-arm64.inc
require conf/machine/include/rcar-gen5-common.inc

# Device tree for Ironhide
KERNEL_DEVICETREE_IRONHIDE = " \
    renesas/r8a78000-ironhide.dtb \
    renesas/r8a78000-ironhide-uio.dtb \
    renesas/r8a78000-ironhide-uio-ca.dtb \
    renesas/r8a78000-ironhide-coresight.dtb \
"
# Device tree for Perceptor
KERNEL_DEVICETREE_PERCEPTOR = " \
    renesas/r8j78070-perceptor.dtb \
    renesas/r8j78070-perceptor-uio.dtb \
    renesas/r8j78070-perceptor-uio-ca.dtb \
    renesas/r8a78000-ironhide-uio-multifwk.dtb \
"
# Device tree for X5H on Evaluation boards common
KERNEL_DEVICETREE ??= "${KERNEL_DEVICETREE_IRONHIDE} ${KERNEL_DEVICETREE_PERCEPTOR}"

# X5H on Evaluation board u-boot configure
UBOOT_CONFIG ??= "ironhide perceptor"
UBOOT_CONFIG[ironhide] = "r8a78000_ironhide_defconfig"
UBOOT_CONFIG[perceptor] = "r8j78070_perceptor_defconfig"

SECURITY_BSP_MACHINE ??= "ironhide perceptor"

EXTRA_IMAGEDEPENDS += "arm-trusted-firmware optee-os"

# Use Poky default util-linux version to 2.41
PREFERRED_VERSION_util-linux = "2.41"

DEFAULTTUNE ?= "cortexa53"
require conf/machine/include/tune-cortexa53.inc
require conf/machine/include/rcar-common.inc

# Device tree for V3x
KERNEL_DEVICETREE = " \
    renesas/r8a77970-eagle.dtb \
    renesas/r8a77970-es1-v3msk.dtb \
    renesas/r8a77970-v3msk.dtb \
    renesas/r8a77980-es2-condor.dtb \
    renesas/r8a77980-condor.dtb \
    renesas/r8a77980-es2-v3hsk.dtb \
    renesas/r8a77980-v3hsk.dtb \
"

# V3x u-boot configuration
UBOOT_CONFIG ??= "condor eagle v3hsk v3msk"
UBOOT_CONFIG[condor] = "r8a77980_condor_defconfig"
UBOOT_CONFIG[eagle] = "r8a77970_eagle_defconfig"
UBOOT_CONFIG[v3hsk] = "r8a77980_v3hsk_defconfig"
UBOOT_CONFIG[v3msk] = "r8a77970_v3msk_defconfig"

# Set Image & SDK name for V3x
IMAGE_NAME = "${IMAGE_BASENAME}-v3x${IMAGE_VERSION_SUFFIX}"
IMAGE_LINK_NAME = "${IMAGE_BASENAME}-v3x"
SDK_NAME_r8a77980 = "${DISTRO}-${TCLIBC}-${SDKMACHINE}-${IMAGE_BASENAME}-${TUNE_PKGARCH}-v3x"
SDK_NAME_r8a77970 = "${DISTRO}-${TCLIBC}-${SDKMACHINE}-${IMAGE_BASENAME}-${TUNE_PKGARCH}-v3x"

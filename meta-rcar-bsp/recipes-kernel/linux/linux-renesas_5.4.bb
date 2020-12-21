DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "(falcon|condor|eagle)"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4.72/rcar-4.1.0.rc5"
SRCREV = "f8c69fa854ff67b0f1b21b30df17429fe2b2eecd"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "5.4.72"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

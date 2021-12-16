DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "(falcon|condor|eagle|white-hawk)"

SRCREV = "${RENESAS_BSP_SRCREV}"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH}"

LINUX_VERSION ?= "5.4.72"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

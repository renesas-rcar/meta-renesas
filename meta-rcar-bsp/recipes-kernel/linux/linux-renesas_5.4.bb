DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "falcon"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4/rcar-4.0.1.rc1"
SRCREV = "7f6e5e961032c3a4395650197633f332d3efaef9"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "5.4.0"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:"
COMPATIBLE_MACHINE = "salvator-x"

RENESAS_BSP_URL = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.4/rcar-3.2.x"
SRCREV = "6aa9863ad91c394d56c693107605babc5de20a37"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "4.4"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

SRC_URI_append = " \
    file://defconfig \
    file://touch.cfg \
"

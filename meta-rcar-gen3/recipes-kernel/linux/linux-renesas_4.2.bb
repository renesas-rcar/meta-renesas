DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:"
COMPATIBLE_MACHINE = "salvator-x"

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.2/rcar-3.0.x"
SRCREV = "c95631010bd6354ccb0bd6a1ee19abad0e19b138"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "4.2"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

SRC_URI_append_salvator-x = " \
    file://defconfig \
"

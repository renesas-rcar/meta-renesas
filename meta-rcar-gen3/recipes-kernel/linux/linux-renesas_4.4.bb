DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

require include/avb-control.inc
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:"
COMPATIBLE_MACHINE = "salvator-x"

RENESAS_BSP_URL = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.4/rcar-3.2.x"
SRCREV = "c2e607873caa620d5fd7e42cfe91f11166d6cdc0"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "4.4"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

SRC_URI_append = " \
    file://defconfig \
    file://touch.cfg \
    ${@base_conditional("USE_AVB", "1", " file://usb-video-class.cfg", "", d)} \
"

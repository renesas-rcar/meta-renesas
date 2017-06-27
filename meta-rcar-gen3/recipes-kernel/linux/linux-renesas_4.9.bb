DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

require include/avb-control.inc
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
COMPATIBLE_MACHINE = "salvator-x|h3ulcb|m3ulcb|draak"

RENESAS_BSP_URL = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.9/rcar-3.5.5"
SRCREV = "58e9b7349e1a28f2ef830f9164112fb17ba31426"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "4.9.0"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# D3 patches for kernel
SRC_URI_append = " \
    file://D3_BSP_patches.scc \
"

SRC_URI_append = " \
    file://defconfig \
    file://touch.cfg \
    ${@base_conditional("USE_AVB", "1", " file://usb-video-class.cfg", "", d)} \
"

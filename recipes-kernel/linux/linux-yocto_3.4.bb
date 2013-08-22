require linux.inc
require linux-dtb.inc

DESCRIPTION = "Linux kernel for the koelsch board"
COMPATIBLE_MACHINE = "koelsch"

PR = "r1"
PV_append = "+git${SRCREV}"

SRCREV = "77d929639b4354d660fd3b6d808603ab625f69c5"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.25/rcar-gen2-4"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_koelsch := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "koelsch_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


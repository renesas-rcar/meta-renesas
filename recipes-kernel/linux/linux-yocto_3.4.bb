require linux.inc
require linux-dtb.inc

DESCRIPTION = "Linux kernel for the lager board"
COMPATIBLE_MACHINE = "lager"

PR = "r5"
PV_append = "+git${SRCREV}"

SRCREV = "6250e5a61a718dbb5a2d8b4dd40a57d1d1b6fe96"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.25/rcar-gen2-5;tag=rcar-gen2-5/v0.5.1"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_lager := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "lager_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

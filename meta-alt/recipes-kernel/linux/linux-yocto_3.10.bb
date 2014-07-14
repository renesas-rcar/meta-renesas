require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the alt board"
COMPATIBLE_MACHINE = "alt"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "d18a39b9b72ea70493f3e459d54182a810e6f033"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.5.0 \
	file://0001-arm-alt-Add-vmalloc-384M-to-bootargs-of-DTS.patch"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_alt := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


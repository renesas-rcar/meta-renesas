require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the lager board"
COMPATIBLE_MACHINE = "lager"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "f07627b7200377b36b742e860ca6ebc0f8e3722f"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.4.0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_lager := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


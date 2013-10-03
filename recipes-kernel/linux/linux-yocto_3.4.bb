require linux.inc
require linux-dtb.inc

DESCRIPTION = "Linux kernel for the lager board"
COMPATIBLE_MACHINE = "lager"

PR = "r4"
PV_append = "+git${SRCREV}"

SRCREV = "f49d2c9098ac09ebfe174564259c287f9644fd7f"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.25/rcar-gen2-5 \
	file://0001-lager-CMA-Update-CMA_SIZE_MBYTES-to-64.patch"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_lager := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "lager_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the koelsch board"
COMPATIBLE_MACHINE = "koelsch"

PR = "r10"
PV_append = "+git${SRCREV}"

SRCREV = "9f6604a1e9161150c39e637ff695328e15cc187d"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.46/rcar-gen2-5.6"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_koelsch := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "koelsch_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the lager board"
COMPATIBLE_MACHINE = "lager"

PV_append = "+git${SRCREV}"

SRCREV = "60a68fdace3ae725a845f8cff8b7a173a289cf6b"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.0.0 \
	file://0001-arm-shmobile-lager-Change-baudrate-to-38400.patch \
"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_lager := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "lager_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


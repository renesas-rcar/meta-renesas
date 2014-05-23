require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the koelsch board"
COMPATIBLE_MACHINE = "koelsch"

PV_append = "+git${SRCREV}"

SRCREV = "c39cb0ebc67d1f69f47abd039e56559680c98f1a"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.1.0 \
	file://0001-arm-shmobile-koelsch-Change-baudrate-to-38400.patch \
"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_koelsch := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

EXTRA_OEMAKE += "LOADADDR=0x40008000"


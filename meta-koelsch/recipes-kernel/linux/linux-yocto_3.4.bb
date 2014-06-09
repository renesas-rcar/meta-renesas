require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the koelsch board"
COMPATIBLE_MACHINE = "koelsch"

PR = "r14"
PV_append = "+git${SRCREV}"

SRCREV = "f9a7635bfd356ab8cb391ac45c5e4226bc19a998"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.81/rcar-gen2-8"

WAYLAND_ENABLE ?= "0"
# Append for wayland package
SRC_URI += '${@base_conditional("WAYLAND_ENABLE", "1", " \
        file://0001-v4l2-vsp1-workaround-Do-padding-the-alpha-field-of-t.patch \
        ", "", d)}'

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_koelsch := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "koelsch_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


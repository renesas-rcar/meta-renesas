require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the koelsch board"
COMPATIBLE_MACHINE = "koelsch"

PR = "r12"
PV_append = "+git${SRCREV}"

SRCREV = "83a97c86f457dda2d18a376ddcf35d5ebc98afcd"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.81/rcar-gen2-6"

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


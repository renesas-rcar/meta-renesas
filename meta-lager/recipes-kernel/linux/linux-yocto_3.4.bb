require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the lager board"
COMPATIBLE_MACHINE = "lager"

PR = "r15"
PV_append = "+git${SRCREV}"

SRCREV = "d12dc4c7e7368c29f6710a4bbcc547e2699e9bac"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git;protocol=git;branch=bsp/ltsi-3.4.81/rcar-gen2-7"

WAYLAND_ENABLE ?= "0"
# Append for wayland package
SRC_URI += '${@base_conditional("WAYLAND_ENABLE", "1", " \
        file://0001-v4l2-vsp1-workaround-Do-padding-the-alpha-field-of-t.patch \
        ", "", d)}'
S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_lager := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "lager_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

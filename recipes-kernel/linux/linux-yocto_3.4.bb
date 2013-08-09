require linux.inc
require linux-dtb.inc

DESCRIPTION = "Linux kernel for the koelsch board"
COMPATIBLE_MACHINE = "koelsch"

PR = "r0"
PV_append = "+git${SRCREV}"

SRCREV = "4938ff7a961bcf44ef53c4a928f6cf9c4e6ddb4d"
SRC_URI = "git://git.yoctoproject.org/linux-yocto-3.4.git;protocol=git;branch=ltsi"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend_koelsch := "${THISDIR}/${PN}:"

KERNEL_DEFCONFIG = "koelsch_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

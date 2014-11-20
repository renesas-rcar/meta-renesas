require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the R-Car Generation 2 based board"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager)"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "2eba9c2b1f02bec3295b27f8415575da00d5ffce"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.8.0 \
	file://0001-arm-lager-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-koelsch-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-alt-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-gose-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
"

S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}


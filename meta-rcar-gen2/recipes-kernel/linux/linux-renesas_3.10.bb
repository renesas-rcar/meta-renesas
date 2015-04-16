require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc
require ../../include/gles-control.inc
require ../../include/multimedia-control.inc

DESCRIPTION = "Linux kernel for the R-Car Generation 2 based board"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk)"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "7e99b78788ab950dc6d33699a739da689ffdf326"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.9.3 \
	file://0001-arm-lager-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-koelsch-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-alt-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-gose-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
"

SRC_URI_append = " \
    ${@' file://drm-rcar-du.cfg' \
        if '${USE_MULTIMEDIA}' == '0' or '${USE_GLES_WAYLAND}' == '0' else ''}"

SRC_URI_append_lcb = " \
	file://0001-ARM-rmobile-Add-SILK-board-support.patch \
	file://0002-ARM-shmobile-r8a7791-add-CAN-DT-support.patch \
	file://0003-can-add-Renesas-R-Car-CAN-driver.patch \
	file://0004-sh-pfc-r8a7791-add-CAN-pin-groups.patch \
	file://0005-sh-pfc-r8a7791-fix-CAN-pin-groups.patch \
	file://0006-can-rcar_can-support-all-input-clocks.patch \
	file://0007-can-rcar_can-document-device-tree-bindings.patch \
	file://0008-can-rcar_can-add-device-tree-support.patch \
	file://0009-ARM-rmobile-Add-Porter-board-support.patch \
	file://0010-i2c-rcar-Revert-i2c-rcar-Support-ACK-by-HW-auto-rest.patch \
	file://0011-i2c-busses-rcar-Workaround-arbitration-loss-error.patch \
	file://0012-gpu-rcar-du-add-RGB-connector.patch \
	file://0013-gpu-rcar-du-Set-interlace-to-false-by-default-for-r8.patch \
	file://0014-ARM-shmobile-defconfig-Enable-SCI-DMA-support.patch \
	file://0015-ARM-shmobile-defconfig-Enable-Bluetooth.patch \
	file://0016-ARM-shmobile-defconfig-Add-ATAG-DTB-compatibility.patch \
"

SRC_URI_append_porter = " file://can.cfg"

S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

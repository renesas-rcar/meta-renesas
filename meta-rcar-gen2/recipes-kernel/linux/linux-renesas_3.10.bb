require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the R-Car Generation 2 based board"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk)"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "b8ca8c397343f4233f9f68fc3a5bf8e1c9b88251"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.9.2 \
	file://0001-arm-lager-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-koelsch-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-alt-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-gose-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
"

SRC_URI_append_porter = " \
	file://0001-kernel-Silk-board-support.patch \
	file://0002-kernel-silk-fix-ethernet-phy-irq.patch \
	file://0003-kernel-silk-fix-sd-detect.patch \
	file://0004-kernel-Revert-i2c-rcar-Support-ACK-by-HW-auto-restart-after-NACK.patch \
	file://0006-Rcar-DU-add-RGB-connector.patch \
	file://0007-SILK-add-i2c0.patch \
	file://0008-Porter-board-support.patch \
	file://0009-shmobile-add-atag-dtb-compat.patch \
	file://0010-Silk-Add-missing-pins-handle-to-Eth.patch \
	file://0011-Silk-Add-missing-DU-pins.patch \
	file://0012-can-add-Renesas-R-Car-CAN-driver.patch \
	file://0013-sh-pfc-r8a7791-add-CAN-pin-groups.patch \
	file://0014-sh-pfc-r8a7791-fix-CAN-pin-groups.patch \
	file://0015-can-rcar_can-support-all-input-clocks.patch \
	file://0016-can-rcar_can-document-device-tree-bindings.patch \
	file://0017-can-rcar_can-add-device-tree-support.patch \
	file://0018-porter-can-support.patch \
	file://0019-i2c-busses-rcar-Workaround-arbitration-loss-error.patch \
	file://0020-Silk-Remove-I2C1-clock-from-clk_enables.patch \
"

SRC_URI_append_silk = " \
	file://0001-kernel-Silk-board-support.patch \
	file://0002-kernel-silk-fix-ethernet-phy-irq.patch \
	file://0003-kernel-silk-fix-sd-detect.patch \
	file://0004-kernel-Revert-i2c-rcar-Support-ACK-by-HW-auto-restart-after-NACK.patch \
	file://0006-Rcar-DU-add-RGB-connector.patch \
	file://0007-SILK-add-i2c0.patch \
	file://0008-Porter-board-support.patch \
	file://0009-shmobile-add-atag-dtb-compat.patch \
	file://0010-Silk-Add-missing-pins-handle-to-Eth.patch \
	file://0011-Silk-Add-missing-DU-pins.patch \
	file://0012-can-add-Renesas-R-Car-CAN-driver.patch \
	file://0013-sh-pfc-r8a7791-add-CAN-pin-groups.patch \
	file://0014-sh-pfc-r8a7791-fix-CAN-pin-groups.patch \
	file://0015-can-rcar_can-support-all-input-clocks.patch \
	file://0016-can-rcar_can-document-device-tree-bindings.patch \
	file://0017-can-rcar_can-add-device-tree-support.patch \
	file://0018-porter-can-support.patch \
	file://0019-i2c-busses-rcar-Workaround-arbitration-loss-error.patch \
	file://0020-Silk-Remove-I2C1-clock-from-clk_enables.patch \
"

S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

DESCRIPTION = "Linux kernel for the R-Car V2H board"

#require include/avb-control.inc
require recipes-kernel/linux/linux-yocto.inc
require linux-dtb-append.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:"
COMPATIBLE_MACHINE = "(blanche|wheat)"

RENESAS_BSP_URL = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.6/rcar-3.3.x"
SRCREV = "f100fac1e2a41c8f0d52f7b5607472a5e5e7c010"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "4.6"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r0"

SRC_URI_append = " \
    file://defconfig \
    file://0001-arm64-renesas-Add-H3ULCB-board.patch \
    file://0002-staging-boards-Add-H3ULCB-staging.patch \
    file://0003-spi-sh-msiof-fixes.patch \
    file://0004-spi-spidev-add-spi-gpio-into-spidev.patch \
    file://0005-spi-spi-gpio-fix-CPOL-mode.patch \
    file://0006-xhci-rcar-add-firmware-for-R-Car-H2-M2-USB-3.0-host-.patch \
    file://0007-usb-host-xhci-plat-add-support-for-the-R-Car-H3-xHCI.patch \
    file://0008-spi-spi-gpio-fix-set-CPOL-default-inverted.patch \
    file://0011-arm64-renesas-Add-M3ULCB-board.patch \
    file://0012-staging-boards-Add-M3ULCB-staging.patch \
    file://0019-can-rcar_can-add-enable-and-standby-control-pins.patch \
    file://0020-can-rcar-canfd-Add-Renesas-R-Car-CAN-FD-driver.patch \
    file://0021-arm64-dts-r8a7795-Add-CAN-FD-support.patch \
    file://0022-can-rcar_canfd-use-explicit-clock_select-from-dts.patch \
    file://0023-can-rcar_canfd-add-enable-and-standby-control-pins.patch \
    file://0024-mtd-Add-RPC-HyperFlash-driver.patch \
    file://0025-IMR-driver-interim-patch.patch \
    file://0040-H3-Maxim-MAX9286-support-support-10635-10640-cameras.patch \
    file://0050-arm64-renesas-Salvator-X-View-H3-board-support.patch \
    file://0051-arm64-renesas-H3ULCB-HAD-support.patch \
    file://0052-arm64-renesas-H3ULCB-View-board-support.patch \
    file://0053-arm64-renesas-Salvator-X-View-M3-board-support.patch \
    file://0054-arm64-renesas-M3ULCB-View-board-support.patch \
    file://v2h.include/0001-ARM-dts-r8a7792-add-clock-index-macros.patch \
    file://v2h.include/0001-ARM-dts-r8a7792-add-power-domain-index-macros.patch \
    file://v2h.dtsi/0001-ARM-dts-r8a7792-initial-SoC-device-tree.patch \
    file://v2h.dtsi/0002-ARM-dts-r8a7792-add-SYS-DMAC-support.patch \
    file://v2h.dtsi/0003-ARM-dts-r8a7792-add-H-SCIF-support.patch \
    file://v2h.dtsi/0004-ARM-dts-r8a7792-add-IRQC-support.patch \
    file://v2h.dtsi/0005-ARM-dts-r8a7792-add-JPU-clocks.patch \
    file://v2h.dtsi/0006-ARM-dts-r8a7792-add-JPU-support.patch \
    file://v2h.dtsi/0007-ARM-dts-r8a7792-add-SMP-support.patch \
    file://v2h.dtsi/0008-ARM-dts-r8a7792-add-PLL1-divided-by-2-clock.patch \
    file://v2h.dtsi/0009-ARM-dts-r8a7792-remove-ADSP-clock.patch \
    file://v2h.dtsi/0010-ARM-dts-r8a7792-add-PFC-support.patch \
    file://v2h.dtsi/0011-ARM-dts-r8a7792-add-GPIO-clocks.patch \
    file://v2h.dtsi/0012-ARM-dts-r8a7792-add-GPIO-support.patch \
    file://v2h.dtsi/0013-ARM-dts-r8a7792-add-EtherAVB-clocks.patch \
    file://v2h.dtsi/0014-ARM-dts-r8a7792-add-EtherAVB-support.patch \
    file://v2h.dtsi/0015-ARM-dts-r8a7792-add-CAN-clocks.patch \
    file://v2h.dtsi/0016-ARM-dts-r8a7792-add-CAN-support.patch \
    file://v2h.dtsi/0017-ARM-dts-r8a7792-add-SD-clocks.patch \
    file://v2h.dtsi/0018-ARM-dts-r8a7792-add-SDHI-support.patch \
    file://v2h.dtsi/0019-ARM-dts-r8a7792-add-I2C-clocks.patch \
    file://v2h.dtsi/0020-ARM-dts-r8a7792-add-I2C-support.patch \
    file://v2h.dtsi/0021-ARM-dts-r8a7792-add-VIN-clocks.patch \
    file://v2h.dtsi/0022-ARM-dts-r8a7792-add-VIN-support.patch \
    file://v2h.dtsi/0023-ARM-dts-r8a7792-fix-misindented-line.patch \
    file://v2h.dtsi/0024-ARM-dts-r8a7792-add-DU-clocks.patch \
    file://v2h.dtsi/0025-ARM-dts-r8a7792-add-DU-support.patch \
    file://v2h.dtsi/0026-ARM-dts-r8a7792-add-VSP1V-clocks.patch \
    file://v2h.dtsi/0027-ARM-dts-r8a7792-add-VSP1V-support.patch \
    file://v2h.dtsi/0028-ARM-dts-r8a7792-add-QSPI-clock.patch \
    file://v2h.dtsi/0029-ARM-dts-r8a7792-add-QSPI-support.patch \
    file://v2h.dtsi/0030-ARM-dts-r8a7792-add-MSIOF-clocks.patch \
    file://v2h.dtsi/0031-ARM-dts-r8a7792-add-MSIOF-support.patch \
    file://v2h.dtsi/0032-ARM-dts-r8a7792-add-VSPM-support.patch \
    file://v2h.dtsi/0033-ARM-dts-r8a7792-add-SGX-support.patch \
    file://v2h.dts/0001-ARM-dts-blanche-initial-device-tree.patch \
    file://v2h.dts/0002-ARM-dts-blanche-add-Ethernet-support.patch \
    file://v2h.dts/0003-ARM-dts-blanche-add-SCIF0-3-pins.patch \
    file://v2h.dts/0004-ARM-dts-blanche-add-Ethernet-pins.patch \
    file://v2h.dts/0005-ARM-dts-blanche-add-CAN0-support.patch \
    file://v2h.dts/0006-ARM-dts-blanche-add-support-for-general-purpose-swit.patch \
    file://v2h.dts/0007-ARM-dts-blanche-add-SDHI0-support.patch \
    file://v2h.dts/0008-ARM-dts-blanche-add-DU-support.patch \
    file://v2h.dts/0009-ARM-dts-blanche-add-support-for-general-purpose-LEDs.patch \
    file://v2h.dts/0010-ARM-dts-wheat-initial-device-tree.patch \
    file://v2h.dts/0011-ARM-dts-wheat-add-Ethernet-support.patch \
    file://v2h.dts/0012-ARM-dts-wheat-add-CAN-support.patch \
    file://v2h.dts/0013-ARM-dts-wheat-add-SDHI0-support.patch \
    file://v2h.dts/0014-ARM-dts-wheat-add-QSPI-support.patch \
    file://v2h.dts/0015-ARM-dts-wheat-add-support-for-tactile-switches.patch \
    file://v2h.dts/0016-ARM-dts-wheat-add-DU-support.patch \
    file://v2h.dts/0017-ARM-dts-wheat-fix-DU-support.patch \
    file://v2h.dts/0018-ARM-dts-wheat-add-max9272-ov10635-cameras.patch \
    file://v2h.configs/0001-ARM-shmobile-defconfig-Enable-r8a7792-SoC.patch \
    file://v2h.configs/0002-ARM-multi_v7_defconfig-defconfig-Enable-r8a7792-SoC.patch \
    file://v2h.mach-shmobile/0001-ARM-shmobile-r8a7792-basic-SoC-support.patch \
    file://v2h.mach-shmobile/0002-ARM-shmobile-rcar-gen2-Obtain-extal-frequency-from-D.patch \
    file://v2h.mach-shmobile/0003-ARM-shmobile-rcar-gen2-Correct-arch-timer-frequency-.patch \
    file://v2h.arm/0001-ARM-debug-ll-Add-support-for-r8a7992.patch \
    file://v2h.drivers.soc/0001-soc-renesas-rcar-sysc-add-R8A7792-support.patch \
    file://v2h.drivers.pinctrl/0000-pinctrl-sh-pfc-Fix-overly-long-lines.patch \
    file://v2h.drivers.pinctrl/0001-pinctrl-sh-pfc-Add-R8A7792-PFC-support.patch \
    file://v2h.drivers.pinctrl/0002-pinctrl-sh-pfc-r8a7792-Add-EtherAVB-pin-groups.patch \
    file://v2h.drivers.pinctrl/0003-pinctrl-sh-pfc-r8a7792-Add-SDHI-pin-groups.patch \
    file://v2h.drivers.pinctrl/0004-pinctrl-sh-pfc-r8a7792-Add-CAN-pin-groups.patch \
    file://v2h.drivers.pinctrl/0005-pinctrl-sh-pfc-r8a7792-Add-VIN-pin-groups.patch \
    file://v2h.drivers.pinctrl/0006-pinctrl-sh-pfc-r8a7792-Add-DU-pin-groups.patch \
    file://v2h.drivers.clk/0001-clk-renesas-Add-R8A7792-support.patch \
    file://v2h.drivers.spi/0001-spi-sh-msiof-Fix-calculation-of-the-division-value.patch \
    file://v2h.drivers.gpio/0001-gpio-rcar-add-R8A7792-support.patch \
    file://v2h.drivers.media.rcar-vin/0001-media-rcar-vin-add-Renesas-R-Car-VIN-driver.patch \
    file://v2h.drivers.media.rcar-vin/0002-media-rcar-vin-get-rid-of-an-unused-var.patch \
    file://v2h.drivers.media.rcar-vin/0003-media-media-rcar-vin-pad-aware-driver-initialisation.patch \
    file://v2h.drivers.media.rcar-vin/0004-media-media-rcar_vin-Use-correct-pad-number-in-try_f.patch \
    file://v2h.drivers.media.rcar-vin/0005-media-media-rcar-vin-add-DV-timings-support.patch \
    file://v2h.drivers.media.rcar-vin/0006-media-rcar-vin-add-R8A7792-SoC.patch \
    file://v2h.drivers.ethernet/0001-net-ethernet-smsc9111x-backport-from-4.8-kernel.patch \
    file://v2h.drivers.gpu/0001-gpu-rcar-du-add-R8A7792-support.patch \
    file://v2h.drivers.gpu/0003-drm-bridge-adv7511-add-support-for-the-2nd-chip.patch \
    file://v2h.drivers.mmc/0001-Revert-mmc-sh_mobile_sdhi-Add-eMMC-HS400-mode-suppor.patch \
    file://v2h.drivers.mmc/0002-Revert-mmc-sh_mobile_sdhi-Add-r8a7796-support.patch \
    file://v2h.drivers.mmc/0003-Revert-mmc-sh_mobile_sdhi-Replace-the-voltage-change.patch \
    file://v2h.drivers.mmc/0004-Revert-mmc-sh_mobile_sdhi-Add-eMMC-support-for-r8a77.patch \
    file://v2h.drivers.mmc/0005-Revert-mmc-sh_mobile_sdhi-Add-UHS-I-mode-support-for.patch \
    file://v2h.drivers.media.i2c/0001-media-i2c-add-max9272-ov10635-camera-support.patch \
    file://v2h.drivers.media.vsp1/0001-media-vsp1-add-R8A7792-VSP1V-support.patch \
"

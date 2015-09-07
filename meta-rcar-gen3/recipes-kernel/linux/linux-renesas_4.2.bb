DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:"
COMPATIBLE_MACHINE = "salvator-x"

RENESAS_BACKPORT_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/geert/renesas-drivers.git"

SRCREV = "d7ba9d1e0b037c24ee7f278fdcf9c44f80f3dbe7"

SRC_URI = "${RENESAS_BACKPORT_URL};protocol=git;nocheckout=1;nobranch=1"

LINUX_VERSION ?= "4.2-rc7"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

SRC_URI_append_salvator-x = " \
    file://0001-Revert-Local-Hack-enable-Initramfs.patch \
    file://0002-arm64-dts-r8a7795-Add-PSCI-node.patch \
    file://0003-arm64-dts-r8a7795-Add-Cortex-A57-CPU-cores.patch \
    file://0004-arm64-dts-salvatorx-Add-EXTAL-clock-override.patch \
    file://0005-arm64-dts-r8a7795-Fix-timer-frequency.patch \
    file://0006-arm64-dts-salvatorx-Add-console-setting-in-bootargs.patch \
    file://0007-pinctrl-sh-pfc-Add-r8a7795-support.patch \
    file://0008-arm64-dts-r8a7795-Add-PFC-node.patch \
    file://0009-pinctrl-sh-pfc-r8a7795-Add-I2C-pins-groups-and-funct.patch \
    file://0010-arm64-dts-r8a7795-Add-remaining-SCIF-nodes.patch \
    file://0011-arm64-dts-salvatorx-Add-serial-ports-support.patch \
    file://0012-Revert-palladium-clk-shmobile-add-initial-settings-f.patch \
    file://0013-gpio-rcar-Add-ARM64-dependence.patch \
    file://0014-arm64-dts-r8a7795-Add-GPIO-nodes.patch \
    file://0015-arm64-defconfig-Enable-R-Car-GPIO.patch \
    file://0016-i2c-rcar-Add-r8a7795-support.patch \
    file://0017-arm64-defconfig-Add-i2c-rcar-support.patch \
    file://0018-arm64-dts-salvatorx-Add-i2c-rcar-support.patch \
    file://0019-arm64-dts-r8a7795-Add-i2c-rcar-support.patch \
    file://0020-net-Fix-CONFIG_NET_VENDOR_RENESAS-for-R8A7795.patch \
    file://0021-arm64-dts-r8a7795-Add-Ethernet-AVB-support.patch \
    file://0022-arm64-dts-salvatorx-Add-Ethernet-AVB-support.patch \
    file://0023-ravb-Add-r8a7795.patch \
    file://0024-ravb-Add-DRV-control-and-clk-setting-for-r8a7795.patch \
    file://0025-arm64-dts-salvatorx-Add-ravb-phy-max-seed-100.patch \
    file://0026-ravb-Add-RAVB-config-for-R-Car-Gen3.patch \
    file://0027-ravb-Add-enable-MICREL_PHY-config-for-R-Car-Gen3.patch \
    file://0028-ravb-Fix-the-first-argument-for-DMA-API.patch \
    file://0029-ravb-Fix-int-mask-value-overwritten-issue.patch \
    file://0030-ravb-tx-timeout-at-no-need-tx-align.patch \
    file://0031-ravb-remove-unhandle-int-cause.patch \
    file://0032-arm64-dts-salvatore-Add-ethernet-aliase.patch \
    file://0033-mmc-sh_mobile_sdhi-Add-EXT_ACC-register-busy-check.patch \
    file://0034-mmc-tmio-Add-SDCLK-enable-after-reset.patch \
    file://0035-mmc-sh_mobile_sdhi-Add-UHS-I-mode-support.patch \
    file://0036-mmc-tmio-Add-SD-clock-start-stop-wait-pass-option.patch \
    file://0037-mmc-tmio-Add-UHS-I-mode-support.patch \
    file://0038-mmc-tmio-Add-error-check-for-data-access-end.patch \
    file://0039-mmc-sdhi-tmio-rcar-Add-r8a7795-support.patch \
    file://0040-mmc-sdhi-rcar-Add-ARM64-dependance.patch \
    file://0041-arm64-defconfig-Add-sdhi-rcar-support.patch \
    file://0042-arm64-dts-r8a7795-Add-sdhi-rcar-support.patch \
    file://0043-arm64-dts-salvatorx-Add-sdhi-rcar-support.patch \
    file://0044-media-vsp1-Add-R-Car-Gen3-device-support.patch \
    file://0045-media-vsp1-Add-BRU4-control-support.patch \
    file://0046-media-vsp1-Add-alpha-blending-supoprt.patch \
    file://0047-media-vsp1-Set-format-to-RPF-input-source.patch \
    file://0048-media-vsp1-Add-display-list-support.patch \
    file://0049-drm-bridge-dw_hdmi-fix-register-I2CM_ADDRESS-registe.patch \
    file://0050-drm-bridge-dw_hdmi-add-dw-hdmi-i2c-bus-adapter-suppo.patch \
    file://0051-drm-bridge-dw_hdmi-Fix-R-Car-Gen3-device-support.patch \
    file://0052-drivers-Makefile-Fix-media-driver-startup-before-gpu.patch \
    file://0053-drm-rcar-du-Add-GEN3-and-R8A7793-dependence.patch \
    file://0054-drm-rcar-du-Add-R8A7795-device-support.patch \
    file://0055-drm-rcar-du-Add-dw_hdmi-driver-startup.patch \
    file://0056-drm-rcar-du-Add-LVDS-backlight-support-by-GPIO.patch \
    file://0057-drm-rcar-du-Add-DPLL-support.patch \
    file://0058-drm-rcar-du-Fix-display-registers-for-R-Car-Gen3.patch \
    file://0059-drm-rcar-du-Add-LVDS-startup-for-R-Car-Gen3.patch \
    file://0060-drm-rcar-du-Add-power-management-support.patch \
    file://0061-drm-rcar-du-Fix-max-CRTCs-number-from-3-to-4.patch \
    file://0062-drm-rcar-du-Add-ARM64-dependence.patch \
    file://0063-drm-rcar-du-Add-offset-address-of-display-channel-3.patch \
    file://0064-drm-rcar-du-Fix-VSP-plane-number-per-devices.patch \
    file://0065-drm-rcar-du-Fix-VSP-feed-plane-number.patch \
    file://0066-drm-rcar-du-Fix-third-argument-of-vsp1_du_setup_rpf.patch \
    file://0067-drm-rcar-du-Add-alpha-blending-support.patch \
    file://0068-drm-rcar-du-Fix-compile-warning.patch \
    file://0069-arm64-dts-r8a7795-Add-display-driver-support.patch \
    file://0070-arm64-dts-salvatorx-Add-connector-and-encoder-for-di.patch \
    file://0071-arm64-configs-Enable-R-Car-DU-related-config.patch \
    file://0072-arm64-configs-Disable-CONFIG_ARCH_QCOM.patch \
    file://0073-drm-rcar-du-Add-display-list-support.patch \
    file://0074-staging-board-salvatorx-Acquire-reserved-CMA-region-.patch \
    file://0075-arm64-configs-Enable-CONFIG_STAGING.patch \
    file://0076-staging-board-salvatorx-Export-reserved-CMA-region-f.patch \
    file://0077-phy-rcar-gen3-usb2-Add-R-Car-Gen3-USB2-PHY-driver.patch \
    file://0078-arm64-dts-r8a7795-Add-mstp7-node-and-enable-HSUSB-cl.patch \
    file://0079-arm64-dts-r8a7795-Add-EHCI-012-clocks.patch \
    file://0080-arm64-dts-r8a7795-Add-usb-phy-device-nodes.patch \
    file://0081-arm64-dts-r8a7795-Add-device-nodes-for-USB-2.0-IPs.patch \
    file://0082-arm64-dts-salvatorx-Add-EHCI-OHCI-pins.patch \
    file://0083-arm64-defconfig-Add-CONFIG_PHY_RCAR_GEN3_USB2.patch \
    file://0084-arm64-dts-salvatorx-Update-memory-node-to-4-GiB-map.patch \
    file://0085-arm64-dts-r8a7795-Add-GSX-node.patch \
    file://defconfig \
"

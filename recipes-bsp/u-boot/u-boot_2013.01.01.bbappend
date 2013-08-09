DEFAULT_PREFERENCE=""
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 3}"

SRC_URI += "file://0001-Add-r8a7790-support.patch	\
	file://0002-arm-rmobile-Modify-pin-setting-to-rmobile-style.patch	\
	file://0003-board-lager-Modify-scif0-module-enable-for-SPI-Flash.patch	\
	file://0004-sh-lib-Modify-sh_timer-for-linaro-compiler.patch	\
	file://0005-configs-Modify-CONFIG_SYS_CLK_FREQ-of-lager.patch	\
	file://0006-board-lager-Add-QoS-setting.patch	\
	file://0007-board-lager-Set-a-mac-address-with-a-board-dependent.patch	\
	file://0008-net-Modify-phy-LED-mode-of-sh_eth.patch	\
	file://0009-board-lager-delete-arch_timer-clock-frequency-settin.patch	\
	file://0010-serial-Modify-sys-and-scif-clock-frequency-of-lager.patch	\
	file://0011-net-sh_eth-Add-data-cache-support.patch	\
	file://0012-board-lager-Fix-to-display-board-memory-size.patch	\
	file://0013-board-lager-Fix-to-display-cpu-revision.patch	\
	file://0014-Fix-QoS-setting.patch	\
	file://0015-serial-Add-r8a7791-support.patch	\
	file://0016-net-sh_eth-Add-r8a7791-support.patch	\
	file://0017-arm-rmobile-Add-r8a7791-support.patch	\
	file://0018-board-renesas-Add-koelsch-support.patch	\
	file://0019-rcar_i2c-Add-I2C-driver.patch	\
	file://0020-board-lager-Add-reset-processing.patch	\
	file://0021-board-koelsch-Fix-u-boot-start-address.patch	\
	file://0022-board-koelsch-Fix-QoS-setting.patch	\
	file://0023-board-lager-Fix-QoS-setting.patch	\
	file://0024-mtd-spi-Add-Quad-Access-to-spansion.patch	\
	file://0025-spi-sh_qspi-Add-Quad-Access.patch	\
	file://0026-board-lager-Add-SPI-Quad-Access-configuration.patch	\
	file://0027-board-koelsch-Add-SPI-Quad-Access-configuration.patch	\
	file://0028-arm-rmobile-Fix-r8a7791-support.patch	\
	"

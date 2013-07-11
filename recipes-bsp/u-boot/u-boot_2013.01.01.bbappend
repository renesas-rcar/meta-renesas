DEFAULT_PREFERENCE=""
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 2}"

SRC_URI += "file://0001-Add-r8a7790-support.patch \
	file://0002-arm-rmobile-Modify-pin-setting-to-rmobile-style.patch \
	file://0003-board-lager-Modify-scif0-module-enable-for-SPI-Flash.patch \
	file://0004-sh-lib-Modify-sh_timer-for-linaro-compiler.patch \
	file://0005-configs-Modify-CONFIG_SYS_CLK_FREQ-of-lager.patch \
	file://0006-board-lager-Add-QoS-setting.patch \
	file://0007-board-lager-Set-a-mac-address-with-a-board-dependent.patch \
	file://0008-net-Modify-phy-LED-mode-of-sh_eth.patch \
	file://0009-board-lager-delete-arch_timer-clock-frequency-settin.patch \
	file://0010-serial-Modify-sys-and-scif-clock-frequency-of-lager.patch \
	file://0011-net-sh_eth-Add-data-cache-support.patch \
	file://0012-board-lager-Fix-to-display-board-memory-size.patch \
	file://0013-board-lager-Fix-to-display-cpu-revision.patch \
	file://0014-Fix-QoS-setting.patch \
	"

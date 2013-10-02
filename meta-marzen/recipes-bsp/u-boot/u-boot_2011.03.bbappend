# Find "files" directory
FILESEXTRAPATHS := "${THISDIR}/files"

# R-Car H1 Marzen board patches
SRC_URI += " \
	file://0001-arm-rch1-Add-R-Car-H1-and-Marzen-support.patch \
	file://0002-arm-rch1-Fix-LBSC-CSWCR-setting-value.patch \
	file://0003-arm-rch1-Fix-DBSC3-initialize.patch \
	file://0004-arm-rch1-Fix-bootm-value.patch \
	file://0005-arm-rch1-Add-reset-support.patch \
	file://0006-arm-rch1-Add-reboot-support.patch \
	file://0007-Fix-copyright-description.patch \
	file://0008-configs-marzen-Improve-ethernet-driver-performace.patch \
	file://0009-Remove-white-space.patch \
	file://0010-board-marzen-Add-DDR-DQS-extension-workaround.patch \
	"

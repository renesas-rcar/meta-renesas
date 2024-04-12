#!/bin/sh

SYSFS_UIO_DRIVER="/sys/bus/platform/drivers/uio_pdrv_genirq/"
SYSFS_VSPD_DRIVER="/sys/bus/platform/drivers/vsp1/"
SYSFS_DU_DRIVER="/sys/bus/platform/drivers/rcar-du/"
SYSFS_CSI_DRIVER="/sys/bus/platform/drivers/rcar-csi2/"
SYSFS_VIN_DRIVER="/sys/bus/platform/drivers/rcar-vin/"
SYSFS_DSI_DRIVER="/sys/bus/platform/drivers/rcar-mipi-dsi/"

VSPD0_DEVICE="fea20000.vsp"
VSPD1_DEVICE="fea28000.vsp"
DU_DEVICE="feb00000.display"
CSI0_DEVICE="feaa0000.csi2"
CSI2_DEVICE="fed60000.csi2"
CSI3_DEVICE="fed70000.csi2"
VIN0_DEVICE="e6ef0000.video"
VIN1_DEVICE="e6ef1000.video"
VIN2_DEVICE="e6ef2000.video"
VIN3_DEVICE="e6ef3000.video"
VIN20_DEVICE="e6ed0000.video"
VIN21_DEVICE="e6ed1000.video"
VIN22_DEVICE="e6ed2000.video"
VIN23_DEVICE="e6ed3000.video"
VIN30_DEVICE="e6ed8000.video"
VIN31_DEVICE="e6ed9000.video"
VIN32_DEVICE="e6eda000.video"
VIN33_DEVICE="e6edb000.video"
DSI0_DEVICE="fed80000.dsi-encoder"

if [ "x$1" = "xdefault" ]
then
	echo "Setting up default configuration"
	# Unbind all devices from the UIO driver
	echo $DSI0_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VSPD0_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VSPD1_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $DU_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $CSI0_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $CSI2_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $CSI3_DEVICE  > $SYSFS_UIO_DRIVER/unbind
	echo $VIN0_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN1_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN2_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN3_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN20_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN21_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN22_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN23_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN30_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN31_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN32_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN33_DEVICE > $SYSFS_UIO_DRIVER/unbind

	# BIND them with Linux drivers
	echo $DSI0_DEVICE > $SYSFS_DSI_DRIVER/bind
	echo $VSPD0_DEVICE > $SYSFS_VSPD_DRIVER/bind
	echo $VSPD1_DEVICE > $SYSFS_VSPD_DRIVER/bind
	echo $DU_DEVICE   > $SYSFS_DU_DRIVER/bind
	echo $CSI0_DEVICE  > $SYSFS_CSI_DRIVER/bind
	echo $CSI2_DEVICE  > $SYSFS_CSI_DRIVER/bind
	echo $CSI3_DEVICE  > $SYSFS_CSI_DRIVER/bind
	echo $VIN0_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN1_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN2_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN3_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN20_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN21_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN22_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN23_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN30_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN31_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN32_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN33_DEVICE > $SYSFS_VIN_DRIVER/bind
fi

if [ "x$1" = "xadas" ]
then
	echo "Setting up ADAS configuration"
	# Unbind all devices from the Linux drivers
	echo $DSI0_DEVICE > $SYSFS_DSI_DRIVER/unbind
	echo $VSPD0_DEVICE > $SYSFS_VSPD_DRIVER/unbind
	echo $VSPD1_DEVICE > $SYSFS_VSPD_DRIVER/unbind
	echo $DU_DEVICE   > $SYSFS_DU_DRIVER/unbind
	echo $CSI0_DEVICE  > $SYSFS_CSI_DRIVER/unbind
	echo $CSI2_DEVICE  > $SYSFS_CSI_DRIVER/unbind
	echo $CSI3_DEVICE  > $SYSFS_CSI_DRIVER/unbind
	echo $VIN0_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN1_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN2_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN3_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN20_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN21_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN22_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN23_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN30_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN31_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN32_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN33_DEVICE > $SYSFS_VIN_DRIVER/unbind

	# BIND them with UIO driver
	echo $DSI0_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VSPD0_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VSPD1_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $DU_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $CSI0_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $CSI2_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $CSI3_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN0_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN1_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN2_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN3_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN20_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN21_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN22_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN23_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN30_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN31_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN32_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN33_DEVICE > $SYSFS_UIO_DRIVER/bind
fi


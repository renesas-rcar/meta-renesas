#!/bin/sh

SYSFS_UIO_DRIVER="/sys/bus/platform/drivers/uio_pdrv_genirq/"
SYSFS_VSPD_DRIVER="/sys/bus/platform/drivers/vsp1/"
SYSFS_DU_DRIVER="/sys/bus/platform/drivers/rcar-du/"
SYSFS_CSI_DRIVER="/sys/bus/platform/drivers/rcar-csi2/"
SYSFS_VIN_DRIVER="/sys/bus/platform/drivers/rcar-vin/"
SYSFS_LVDS_DRIVER="/sys/bus/platform/drivers/rcar-lvds/"

VSPD_DEVICE="fea20000.vsp_00"
DU_DEVICE="feb00000.du_00"
CSI0_DEVICE="feaa0000.csi_00"
CSI1_DEVICE="feab0000.csi_01"
VIN0_DEVICE="e6ef0000.vin_00"
VIN1_DEVICE="e6ef1000.vin_01"
VIN2_DEVICE="e6ef2000.vin_02"
VIN3_DEVICE="e6ef3000.vin_03"
VIN4_DEVICE="e6ef4000.vin_04"
VIN5_DEVICE="e6ef5000.vin_05"
VIN6_DEVICE="e6ef6000.vin_06"
VIN7_DEVICE="e6ef7000.vin_07"
LVDS_DEVICE="feb90000.lvds_00"

if [ "x$1" = "xdefault" ]
then
	echo "Setting up default configuration"
	# Unbind all devices from the UIO driver
	echo $LVDS_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VSPD_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $DU_DEVICE   > $SYSFS_UIO_DRIVER/unbind
	echo $CSI0_DEVICE  > $SYSFS_UIO_DRIVER/unbind
	echo $CSI1_DEVICE  > $SYSFS_UIO_DRIVER/unbind
	echo $VIN0_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN1_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN2_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN3_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN4_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN5_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN6_DEVICE > $SYSFS_UIO_DRIVER/unbind
	echo $VIN7_DEVICE > $SYSFS_UIO_DRIVER/unbind

	# BIND them with Linux drivers
	sleep 2
	echo $LVDS_DEVICE > $SYSFS_LVDS_DRIVER/bind
	echo $VSPD_DEVICE > $SYSFS_VSPD_DRIVER/bind
	echo $DU_DEVICE   > $SYSFS_DU_DRIVER/bind
	echo $CSI0_DEVICE  > $SYSFS_CSI_DRIVER/bind
	echo $CSI1_DEVICE  > $SYSFS_CSI_DRIVER/bind
	echo $VIN0_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN1_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN2_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN3_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN4_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN5_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN6_DEVICE > $SYSFS_VIN_DRIVER/bind
	echo $VIN7_DEVICE > $SYSFS_VIN_DRIVER/bind
fi

if [ "x$1" = "xadas" ]
then
	echo "Setting up ADAS configuration"
	# Unbind all devices from the Linux drivers
	echo $DU_DEVICE   > $SYSFS_DU_DRIVER/unbind
	echo $VSPD_DEVICE > $SYSFS_VSPD_DRIVER/unbind
	echo $LVDS_DEVICE > $SYSFS_LVDS_DRIVER/unbind
	echo $CSI0_DEVICE  > $SYSFS_CSI_DRIVER/unbind
	echo $CSI1_DEVICE  > $SYSFS_CSI_DRIVER/unbind
	echo $VIN0_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN1_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN2_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN3_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN4_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN5_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN6_DEVICE > $SYSFS_VIN_DRIVER/unbind
	echo $VIN7_DEVICE > $SYSFS_VIN_DRIVER/unbind

	# BIND them with UIO driver
	echo $DU_DEVICE   > $SYSFS_UIO_DRIVER/bind
	echo $VSPD_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $LVDS_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $CSI0_DEVICE  > $SYSFS_UIO_DRIVER/bind
	echo $CSI1_DEVICE  > $SYSFS_UIO_DRIVER/bind
	echo $VIN0_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN1_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN2_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN3_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN4_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN5_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN6_DEVICE > $SYSFS_UIO_DRIVER/bind
	echo $VIN7_DEVICE > $SYSFS_UIO_DRIVER/bind
fi


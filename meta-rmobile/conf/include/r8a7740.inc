SOC_FAMILY = "r8a7740"
require conf/machine/include/soc-family.inc
require conf/machine/include/tune-cortexa9.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto"
PREFERRED_VERSION_linux-yocto ?= "3.4%"

KERNEL_IMAGETYPE = "zImage"

EXTRA_IMAGEDEPENDS += "u-boot"
PREFERRED_PROVIDER_u-boot ?= "u-boot"
PREFERRED_VERSION_u-boot ?= "v2013.01%"
UBOOT_MACHINE = "armadillo-800eva_config"

MACHINE_FEATURES = "serial mmc alsa ext2 touchscreen usbhost vfat ethernet"
MACHINE_EXTRA_RRECOMMENDS = "kernel-modules"


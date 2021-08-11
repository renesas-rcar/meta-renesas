require conf/machine/include/${SOC_FAMILY}.inc

# 32BIT package install (default is disable)
# This variables can be used only in multilib.
USE_32BIT_PKGS ?= "0"
USE_32BIT_WAYLAND ?= "0"
USE_32BIT_MMP ?= "0"

MACHINE_FEATURES = ""

KERNEL_IMAGETYPE = "Image"
IMAGE_FSTYPES += " tar.bz2 ext4 wic.xz wic.bmap wic.xz.sha256sum"

WKS_FILE ?= "rcar-singlepart-noloader.wks"

SERIAL_CONSOLES = "115200;ttySC0"

# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"

# Configuration for ARM Trusted Firmware
EXTRA_IMAGEDEPENDS += " arm-trusted-firmware optee-os"

# u-boot
PREFERRED_VERSION_u-boot = "v2020.10%"
EXTRA_IMAGEDEPENDS += " u-boot"

# Add variable to Build Configuration in build log
BUILDCFG_VARS:append = " SOC_FAMILY"

# sync is required to build kernel-module-gles
# rsync is required to build kernel 5.4
HOSTTOOLS += "sync rsync"

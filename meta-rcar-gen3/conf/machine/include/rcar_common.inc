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
OLDEST_KERNEL = "5.10"

# Configuration for ARM Trusted Firmware
EXTRA_IMAGEDEPENDS += " arm-trusted-firmware optee-os"
# Add default config to build eMMC boot mode (it should enable USE_EMMC_BOOTMODE = "1" local.conf)
USE_EMMC_BOOTMODE ?= "0"

# u-boot
PREFERRED_VERSION_u-boot = "v2020.10%"
EXTRA_IMAGEDEPENDS += " u-boot"

# Add variable to Build Configuration in build log
BUILDCFG_VARS:append = " SOC_FAMILY"

# sync is required to build kernel-module-gles
# rsync is required to build kernel 5.4
HOSTTOOLS += "sync rsync"

# for gstreamer
PREFERRED_VERSION_gstreamer1.0 = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-libav = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-meta-base = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-omx = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-plugins-base = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-plugins-bad = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-plugins-good = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"
PREFERRED_VERSION_gstreamer1.0-plugins-ugly = "${@bb.utils.contains('MACHINE_FEATURES', 'multimedia', '1.20.3', '', d)}"

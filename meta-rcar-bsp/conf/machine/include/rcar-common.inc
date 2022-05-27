require conf/machine/include/${SOC_FAMILY}.inc

MACHINE_FEATURES = ""

KERNEL_IMAGETYPE = "Image"
IMAGE_FSTYPES += "tar.bz2 wic.gz wic.bmap wic.gz.sha256sum wic.gz.crc"
WKS_FILE ?= "image-v3x.wks"

COMPRESSIONTYPES += "crc"
CONVERSION_CMD_crc = "${RECIPE_SYSROOT_NATIVE}/usr/bin/perl-native/perl \
                      ${RECIPE_SYSROOT_NATIVE}/usr/bin/crc32 \
		      ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type} \
		      > ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.crc"

SERIAL_CONSOLES = "115200;ttySC0"

# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"

# u-boot
EXTRA_IMAGEDEPENDS += " u-boot"

# Add variable to Build Configuration in build log
BUILDCFG_VARS_append = " SOC_FAMILY"

# rsync is required to build kernel 5.4
HOSTTOOLS += "rsync"

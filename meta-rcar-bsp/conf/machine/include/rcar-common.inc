require conf/machine/include/${SOC_FAMILY}.inc

MACHINE_FEATURES = ""

KERNEL_IMAGETYPE = "Image"
IMAGE_FSTYPES_append = " tar.bz2 ext4"

SERIAL_CONSOLES = "115200;ttySC0"

# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"

# u-boot
PREFERRED_VERSION_u-boot = "v2020.01%"
EXTRA_IMAGEDEPENDS += " u-boot"

# Add variable to Build Configuration in build log
BUILDCFG_VARS_append = " SOC_FAMILY"

# rsync is required to build kernel 5.4
HOSTTOOLS += "rsync"

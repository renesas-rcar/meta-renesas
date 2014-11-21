SOC_FAMILY = "rcar-gen2:r8a7794"
require conf/machine/include/soc-family.inc
DEFAULTTUNE ?= "cortexa7hf-neon"
require conf/machine/include/tune-cortexa7.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-renesas"
PREFERRED_PROVIDER_linux-libc-headers ?= "linux-libc-headers"
PREFERRED_PROVIDER_nativesdk-linux-libc-headers ?= "nativesdk-linux-libc-headers"
PREFERRED_PROVIDER_u-boot ?= "u-boot"

KERNEL_IMAGETYPE ?= "uImage"
EXTRA_IMAGEDEPENDS += "u-boot"

SOC_FAMILY = "rcar-gen2:r8a7791"
require conf/machine/include/soc-family.inc
DEFAULTTUNE ?= "cortexa15hf-neon"
require conf/machine/include/tune-cortexa15.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-renesas"
PREFERRED_PROVIDER_linux-libc-headers ?= "linux-libc-headers"
PREFERRED_PROVIDER_nativesdk-linux-libc-headers ?= "nativesdk-linux-libc-headers"
PREFERRED_PROVIDER_u-boot ?= "u-boot"

KERNEL_IMAGETYPE ?= "uImage"
EXTRA_IMAGEDEPENDS += "u-boot"

DESCRIPTION = "V3X specific packages"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

PACKAGES = " \
    packagegroup-v3x \
"

# V3x common packages: CMEM, CV lib
RDEPENDS_packagegroup-v3x = " \
    kernel-module-uio-pdrv-genirq \
    kernel-module-cmemdrv \
    kernel-module-cmemdrv-dev \
    kernel-module-cpurttdrv \
    udev-rules-cvlib \
    linux-renesas-uapi \
    bsp-config \
"

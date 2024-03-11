DESCRIPTION = "Rcar Gen5 specific packages"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

PACKAGES = " \
    packagegroup-renesas \
"

CPURTT_PKGS = " \
    kernel-module-cpurttdrv3 \
"

GFX_PKGS = " \
    kernel-module-gles \
    gles-user-module \
    libegl \
    libgles2 \
"

# ADAS common packages: CMEM, CV lib
RDEPENDS_packagegroup-renesas = " \
    kernel-module-uio-pdrv-genirq \
    kernel-module-cmemdrv \
    kernel-module-cmemdrv-dev \
    udev-rules-cvlib \
    linux-renesas-uapi \
    bsp-config \
    capture \
    ${CPURTT_PKGS} \
    kernel-module-qos \
    qosif-user-module \
    qosif-tp-user-module \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gsx', '${GFX_PKGS}', '', d)} \
    nvme-initscripts \
"


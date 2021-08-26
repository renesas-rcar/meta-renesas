DESCRIPTION = "V3X specific packages"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

PACKAGES = " \
    packagegroup-v3x \
"

CPURTT_PKGS_condor = " \
    kernel-module-cpurttdrv \
    kernel-module-cpurttdrv2 \
"
CPURTT_PKGS_eagle = " \
    kernel-module-cpurttdrv \
    kernel-module-cpurttdrv2 \
"
CPURTT_PKGS_falcon = ""

# V3x common packages: CMEM, CV lib, MMNGR
RDEPENDS_packagegroup-v3x = " \
    kernel-module-uio-pdrv-genirq \
    kernel-module-cmemdrv \
    kernel-module-cmemdrv-dev \
    kernel-module-mmngr \
    kernel-module-mmngrbuf \
    mmngr-user-module \
    mmngrbuf-user-module \
    udev-rules-cvlib \
    linux-renesas-uapi \
    bsp-config \
    capture \
    ${CPURTT_PKGS} \
    kernel-module-qos \
    qosif-user-module \
    qosif-tp-user-module \
"

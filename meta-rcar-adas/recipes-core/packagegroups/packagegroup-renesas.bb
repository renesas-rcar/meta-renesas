DESCRIPTION = "V3x/V4x specific packages"

LICENSE = "BSD-3-Clause & GPL-2.0-or-later & LGPL-2.0-or-later"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = " \
    packagegroup-renesas \
"

CPURTT_PKGS:rcar-v3x = ""
CPURTT_PKGS:rcar-v4x = " \
    kernel-module-cpurttdrv3 \
"

GFX_PKGS:rcar-v3x = ""
GFX_PKGS:rcar-v4x = " \
    kernel-module-gles \
    gles-user-module \
    libegl \
    libgles2 \
"

# ADAS common packages: CMEM, CV lib
RDEPENDS:packagegroup-renesas = " \
    kernel-module-uio-pdrv-genirq \
    kernel-module-cmemdrv \
    kernel-module-cmemdrv-dev \
    udev-rules \
    linux-renesas-uapi \
    bsp-config \
    capture \
    ${CPURTT_PKGS} \
    kernel-module-qos \
    qosif-user-module \
    qosif-tp-user-module \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gsx', '${GFX_PKGS}', '', d)} \
"


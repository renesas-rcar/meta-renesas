require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4/rcar-4.0.0"
SRCREV = "289de10299119b22a516bd01d9baf60e59351cc1"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Fix patch for Weston 8.0.0 issue
SRC_URI_appen_rcar-gen3 = " \
    file://0001-drm-rcar-du-Set-primary-plane-zpos-immutably-at-init.patch \
"

# Fix compile error on host installs GCC 10
SRC_URI_append = " \
    file://0001-scripts-dtc-Remove-redundant-YYLOC-global-declaratio.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"


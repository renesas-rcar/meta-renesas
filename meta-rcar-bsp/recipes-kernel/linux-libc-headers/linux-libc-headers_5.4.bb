require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4.72/rcar-4.1.0.rc9"
SRCREV = "e9b2e9d358b5543f5de7a19dd8a1421f56deb466"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

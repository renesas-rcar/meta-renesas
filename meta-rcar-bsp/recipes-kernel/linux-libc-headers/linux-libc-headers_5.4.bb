require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/rcar-kernel-info-common.inc

SRCREV = "${RENESAS_BSP_SRCREV}"

SRC_URI = "${RENESAS_BSP_URL};branch=${RENESAS_BSP_BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

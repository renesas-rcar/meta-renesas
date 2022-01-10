require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/rcar-kernel-info-common.inc

SRCREV = "902a351a2509743fbc0a8c0bbb74476379dec975"

SRC_URI = "${RENESAS_BSP_URL};branch=v5.4.72/rcar-4.1.0.rc18"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

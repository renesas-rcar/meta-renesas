require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/rcar-kernel-info-common.inc

SRCREV = "1fb986598012bd44121608fdf6bb2d9ea0106f61"

SRC_URI = "${RENESAS_BSP_URL};branch=v5.10.41/rcar-5.1.5.pt12"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

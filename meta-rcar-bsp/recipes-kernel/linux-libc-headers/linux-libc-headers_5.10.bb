require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/rcar-kernel-info-common.inc

SRCREV = "3b719462a050b49a2a0c37b9ff95e338bcf2a36d"

SRC_URI = "${RENESAS_BSP_URL};branch=v5.10.41/rcar-5.1.5.pt13"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

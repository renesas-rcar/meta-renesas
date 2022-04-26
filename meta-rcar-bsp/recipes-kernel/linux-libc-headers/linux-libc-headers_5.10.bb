require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/rcar-kernel-info-common.inc

SRCREV = "3717238484030abe808a66fbddec19829c82c007"

SRC_URI = "${RENESAS_BSP_URL};branch=v5.10.41/rcar-5.1.6.rc2"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

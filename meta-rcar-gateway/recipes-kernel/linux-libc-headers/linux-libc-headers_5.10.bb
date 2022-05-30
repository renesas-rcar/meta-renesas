require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.41/rcar-5.1.6.rc3"
SRCREV = "2f11ac9c63f737ebbd3a973ba3396e4135e5ae5d"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.41/rcar-5.1.7.rc12"
SRCREV = "e1ca7cab5340e3cb99f9875f482738b241856103"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH};protocol=https"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

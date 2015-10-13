require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.2/rcar-3.0.x"
SRCREV = "e170c8f8417ea5c4569e893113cc89b46c15b313"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

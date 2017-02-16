require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.9/rcar-3.5.2"
SRCREV = "d4f12792cd97457e13ea4a77b4aa9fb3c28875fe"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"

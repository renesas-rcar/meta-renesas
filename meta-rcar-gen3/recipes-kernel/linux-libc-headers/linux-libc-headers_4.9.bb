require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.9/rcar-3.5.1"
SRCREV = "13e7680774d9103bb2685635238bcf577a52ff96"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"

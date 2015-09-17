require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.2/rcar-3.0.x"
SRCREV = "c95631010bd6354ccb0bd6a1ee19abad0e19b138"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

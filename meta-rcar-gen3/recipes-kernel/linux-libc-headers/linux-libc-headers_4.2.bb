require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.2/rcar-3.0.x"
SRCREV = "8fb9cd14b142163fda02cd29303b0fd165b3402d"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.4/rcar-3.2.x"
SRCREV = "6aa9863ad91c394d56c693107605babc5de20a37"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"

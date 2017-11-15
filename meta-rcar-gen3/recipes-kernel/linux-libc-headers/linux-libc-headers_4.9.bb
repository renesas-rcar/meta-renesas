require include/iccom-control.inc
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.9/rcar-3.5.9"
SRCREV = "2c20c9881d53f1b5c7a3ebf55bb83ec15902d0d0"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Add some patches to support ICCOM module
SRC_URI_append = " \
    ${@base_conditional("USE_ICCOM", "1", " file://iccom_feature.scc", "", d)} \
"
S = "${WORKDIR}/git"

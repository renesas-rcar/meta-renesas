require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.9/rcar-3.5.5"
SRCREV = "58e9b7349e1a28f2ef830f9164112fb17ba31426"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"

# D3 patches for kernel
SRC_URI_append = " \
    file://D3_BSP_patches.scc \
"

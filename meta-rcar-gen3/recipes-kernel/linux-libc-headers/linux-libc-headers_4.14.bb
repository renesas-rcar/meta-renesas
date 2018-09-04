require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.14/rcar-3.7.3"
SRCREV = "c5090444b63749f899a39c7d39fbbd65e614eeba"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Fix micro SD card issue on M3ULCB
SRC_URI_append = " \
    file://0001-Revert-mmc-renesas_sdhi_internal_dmac-limit-DMA-RX-f.patch \
"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@base_conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
"

S = "${WORKDIR}/git"

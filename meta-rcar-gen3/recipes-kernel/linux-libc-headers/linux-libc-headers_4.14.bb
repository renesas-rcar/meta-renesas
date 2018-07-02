require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.14/rcar-3.7.0"
SRCREV = "118adc53e8e9806d76f40859ba96290f289f8839"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Fix micro SD card issue on M3ULCB
SRC_URI_append = " \
    file://0001-Revert-mmc-renesas_sdhi_internal_dmac-limit-DMA-RX-f.patch \
"

# W/A Fix build issue in YP2.5 with Linux v4.14
SRC_URI_append = " \
    file://0001-arm64-bpf-correct-broken-uapi-for-BPF_PROG_TYPE_PERF.patch \
"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
"

S = "${WORKDIR}/git"

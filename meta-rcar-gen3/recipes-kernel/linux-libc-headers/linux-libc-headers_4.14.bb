require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.14/rcar-3.6.2"
SRCREV = "a95d91d636e3dcfdb8279a0293da17e90c871186"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@base_conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
"

# Patches for sysc
SRC_URI_append = " \
    file://0001-soc-renesas-rcar-sysc-Update-power-control-flow-for-.patch \
    file://0002-soc-renesas-rcar-sysc-Add-SYSCEXTMASK-info-for-r8a77.patch \
    file://0003-soc-renesas-rcar-sysc-Add-SYSCEXTMASK-info-for-r8a77.patch \
    file://0004-soc-renesas-rcar-sysc-Add-SYSCEXTMASK-info-for-r8a77.patch \
    file://0005-soc-renesas-rcar-sysc-Add-SYSCEXTMASK-info-for-r8a77.patch \
    file://0006-soc-renesas-rcar-sysc-Add-SYSCEXTMASK-info-for-r8a77.patch \
"

S = "${WORKDIR}/git"

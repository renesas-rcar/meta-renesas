require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.14.70-ltsi-rc1/rcar-3.8.0"
SRCREV = "52f8a317698424fe3a4ba7f88d2b87fc6bf6591d"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@base_conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
"

# Device tree patches
SRC_URI_append = " \
    file://0001-arm64-dts-salvator-common-Rcar-Sound.patch \
    file://0002-arm64-dts-r8a7796-salvator-xs-Remove-Sound-Card.patch \
    file://0003-arm64-dts-r8a7795-salvator-x-Remove-Sound-Card.patch \
    file://0004-arm64-dts-r8a7795-salvator-xs-Remove-Sound-Card.patch \
    file://0005-arm64-dts-r8a7795-es1-salvator-x-Remove-Sound-Card.patch \
    file://0006-arm64-dts-r8a7796-salvator-x-Remove-Sound-Card.patch \
"

S = "${WORKDIR}/git"

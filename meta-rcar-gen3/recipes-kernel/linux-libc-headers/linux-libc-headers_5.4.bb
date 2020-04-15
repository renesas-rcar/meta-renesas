require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4/rcar-3.10.0.rc1"
SRCREV = "52be90f101cd18160cda57d246c1e2e6abfe4a74"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
"

S = "${WORKDIR}/git"


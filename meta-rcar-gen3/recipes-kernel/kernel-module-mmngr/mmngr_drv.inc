LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

require include/rcar-gen3-modules-common.inc
inherit module

MMNGR_DRV_URI = "git://github.com/renesas-rcar/mmngr_drv.git"

SRC_URI = "${MMNGR_DRV_URI};branch=rcar_gen3"
SRCREV = "0cd6bca475c86abde424151e38e2434e131885ed"

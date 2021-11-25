require include/rcar-bsp-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

require include/rcar-bsp-path-common.inc
inherit module

MMNGR_DRV_URI = "git://github.com/renesas-rcar/mmngr_drv.git;protocol=https"

SRC_URI = "${MMNGR_DRV_URI};branch=rcar_gen3_v3x"
SRCREV = "35f3751443cd064883c4c43a6e790b137b5b2b72"

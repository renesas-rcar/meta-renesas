LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

require include/rcar-gen3-modules-common.inc
inherit module

MMNGR_DRV_URI = "git://github.com/renesas-rcar/mmngr_drv.git"

SRC_URI = "${MMNGR_DRV_URI};branch=rcar_gen3"
SRCREV = "f313b1716a6eb276adc5ec7b3db90ec191b65160"

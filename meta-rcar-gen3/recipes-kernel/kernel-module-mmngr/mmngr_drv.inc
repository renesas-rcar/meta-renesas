LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

require include/rcar-gen3-modules-common.inc
inherit module

MMNGR_DRV_URI = "git://github.com/renesas-rcar/mmngr_drv.git"

SRC_URI = "${MMNGR_DRV_URI};branch=rcar_gen3;protocol=https"
SRCREV = "2439802426474136312bd10bc4c143fbf1c84850"

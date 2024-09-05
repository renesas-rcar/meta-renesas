LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

require include/rcar-gen3-modules-common.inc
inherit module

MMNGR_DRV_URI = "git://github.com/renesas-rcar/mmngr_drv.git"

SRC_URI = "${MMNGR_DRV_URI};branch=rcar_gen3_maintenance_2023;protocol=https"
SRCREV = "${@oe.utils.conditional("USE_SAFE_RENDERING", "1", \
    "bd1c9b76cbddf80d5d47255031d6049a6fee21d8", \
    "892443a66c57e20ff546ac8925653636b5c8ea7a", d)}"

DESCRIPTION = "AVB MCH Driver for Linux for the R-Car Gen3"

require include/avb-control.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=495d47d57d37b06c3ad8c08a85f058c7 \
"

inherit module distro_features_check

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-mch.git;branch=rcar-gen3"
SRCREV = "eafc080dce40c171f060e39f4887354e57ba7c1a"

S = "${WORKDIR}/git"

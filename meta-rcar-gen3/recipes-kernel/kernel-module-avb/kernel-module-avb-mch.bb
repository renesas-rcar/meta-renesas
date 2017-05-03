DESCRIPTION = "AVB MCH Driver for Linux for the R-Car Gen3"

require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=96e553279b3fff0d6168dafe0b596197 \
"

inherit module distro_features_check

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-mch.git;branch=rcar-gen3"
SRCREV = "440092412e5e100700c4a9ec36655ed38174b5dd"

S = "${WORKDIR}/git"

includedir = "${RENESAS_DATADIR}/include"

do_install_append() {
    # Create destination directories
    install -d ${KERNELSRC}/include
    install -d ${D}/${includedir}

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR).
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/ravb_mch.h ${KERNELSRC}/include

    # Install shared header files to ${includedir}
    install -m 644 ${S}/ravb_mch.h ${D}/${includedir}
}

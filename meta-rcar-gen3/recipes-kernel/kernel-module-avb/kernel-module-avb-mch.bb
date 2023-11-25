DESCRIPTION = "AVB MCH Driver for Linux for the R-Car Gen3"

require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=96e553279b3fff0d6168dafe0b596197 \
"

inherit module features_check

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-mch.git;branch=rcar-gen3;protocol=https"
SRCREV = "b234c4c867e9e4b9cc7cb00f483a43a36e3d3427"

S = "${WORKDIR}/git"

includedir = "${RENESAS_DATADIR}/include"

# Build AVB MCH kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""


SRC_URI_append = " \
    file://0001_fix_mch_open_wrong_initialization_order.patch \
"

do_install_append() {
    # Create destination directories
    install -d ${D}/${includedir}

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR).
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/ravb_mch.h ${KERNELSRC}/include

    # Install shared header files to ${includedir}
    install -m 644 ${S}/ravb_mch.h ${D}/${includedir}
}

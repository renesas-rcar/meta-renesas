DESCRIPTION = "AVB MCH Driver for Linux for the R-Car Gen3"

require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=96e553279b3fff0d6168dafe0b596197 \
"

inherit module features_check

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-mch.git;branch=rcar-gen3;protocol=https"
SRCREV = "9d2c862b0f950c44ef6b23651783cb05619abfa7"

S = "${WORKDIR}/git"

includedir = "${RENESAS_DATADIR}/include"

# Build AVB MCH kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_install:append() {
    # Create destination directories
    install -d ${D}/${includedir}

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR).
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/ravb_mch.h ${KERNELSRC}/include

    # Install shared header files to ${includedir}
    install -m 644 ${S}/ravb_mch.h ${D}/${includedir}
}

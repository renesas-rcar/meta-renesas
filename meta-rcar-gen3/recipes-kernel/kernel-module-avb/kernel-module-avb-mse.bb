DESCRIPTION = "AVB MSE Driver for Linux for the R-Car Gen3"

require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=98449101c5d5452a9429b88d6ead1ba4 \
"

inherit module features_check

DEPENDS = "linux-renesas kernel-module-avb-streaming kernel-module-avb-mch"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-mse.git;branch=rcar-gen3"
SRCREV = "d290b4a5d7afab654a8b3a356934be2c1406c378"

S = "${WORKDIR}/git"

includedir = "${RENESAS_DATADIR}/include"

# Build AVB MSE kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_install_append() {
    # Create destination directories
    install -d ${KERNELSRC}/include
    install -d ${D}/${includedir}

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR).
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/ravb_mse.h ${KERNELSRC}/include
    install -m 644 ${S}/ravb_mse_kernel.h ${KERNELSRC}/include

    # Install shared header files to ${includedir}
    install -m 644 ${S}/ravb_mse.h ${D}/${includedir}
    install -m 644 ${S}/ravb_mse_kernel.h ${D}/${includedir}
}

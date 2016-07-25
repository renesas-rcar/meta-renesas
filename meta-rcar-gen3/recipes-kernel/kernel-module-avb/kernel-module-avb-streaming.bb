DESCRIPTION = "AVB Streaming Driver for Linux for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc
require include/avb-control.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=704e38c3a28fde2e7fa153d3e0e787a4 \
"

inherit module

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-streaming.git;branch=rcar-gen3"
SRCREV = "ed0d5c1b8dee79d9d6b44e5d660275e20b62e485"

S = "${WORKDIR}/git"

do_install_append () {
    # Create destination directories
    install -d ${KERNELSRC}/include

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR).
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/ravb_eavb.h ${KERNELSRC}/include
}

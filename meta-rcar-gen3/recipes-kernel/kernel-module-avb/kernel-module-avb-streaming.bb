DESCRIPTION = "AVB Streaming Driver for Linux for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc
require include/rcar-gen3-avb-control.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=704e38c3a28fde2e7fa153d3e0e787a4 \
"

inherit module

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-streaming.git;branch=rcar-gen3"
SRCREV = "d72762be09b6f5e2da214c51aa42c876db6b3858"

S = "${WORKDIR}/git"

do_install_append () {
    # Create destination directories
    install -d ${KERNELSRC}/include
    install -d ${D}/usr/src/kernel/include

    # Copy shared header files and export SDK
    install -m 644 ${S}/ravb_eavb.h ${KERNELSRC}/include
    install -m 644 ${S}/ravb_eavb.h ${D}/usr/src/kernel/include
}

FILES_${PN}-dev = " \
    /usr/src/kernel/include/*.h \
"

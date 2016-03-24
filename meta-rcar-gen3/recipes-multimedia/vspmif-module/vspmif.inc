LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

require include/rcar-gen3-modules-common.inc

VSPMIF_LIB_URL = " \
    git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH = "rcar_gen3"
SRCREV = "4ab883c5f78b8f2becf2df93affa081f8ad15ace"

SRC_URI = "${VSPMIF_LIB_URL};branch=${BRANCH}"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

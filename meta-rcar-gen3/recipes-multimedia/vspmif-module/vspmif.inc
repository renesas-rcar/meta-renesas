LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=30a99e0d36a3da1f5cf93c070ad7888a \
"

require include/rcar-gen3-modules-common.inc

VSPMIF_LIB_URL = "git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH = "rcar_gen3"
SRCREV = "3d2c4c93e7d7b723722f4294d5e81339c06aa03a"

SRC_URI = "${VSPMIF_LIB_URL};branch=${BRANCH}"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

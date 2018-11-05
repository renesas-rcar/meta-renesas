LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=30a99e0d36a3da1f5cf93c070ad7888a \
"

require include/rcar-gen3-modules-common.inc

VSPMIF_LIB_URL = "git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH = "rcar_gen3"
SRCREV = "06fe8b1341801079f8a2b964ea95980967dcf789"

SRC_URI = "${VSPMIF_LIB_URL};branch=${BRANCH}"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

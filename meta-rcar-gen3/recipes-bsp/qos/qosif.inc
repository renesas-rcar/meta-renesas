LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=96659f2a7571bfa27483882a653c3bb9 \
"

require include/rcar-gen3-modules-common.inc

QOSIF_LIB_URL = "git://github.com/renesas-rcar/qos_lib.git"
BRANCH = "rcar-gen3"
SRCREV = "129ecbb03b7dac24e92ffa6fc62c183ae372f9ff"

SRC_URI = "${QOSIF_LIB_URL};branch=${BRANCH}"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

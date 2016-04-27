LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

require include/rcar-gen3-modules-common.inc

QOSIF_LIB_URL = "git://github.com/renesas-rcar/qos_lib.git"
BRANCH = "rcar-gen3"
SRCREV = "3659e5e5ac697da992a9ccd6e00678b546039e4d"

SRC_URI = "${QOSIF_LIB_URL};branch=${BRANCH}"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

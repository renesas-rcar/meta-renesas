LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=96659f2a7571bfa27483882a653c3bb9 \
"

require include/rcar-bsp-modules-common.inc

COMPATIBLE_MACHINE = "(spider|s4sk)"
# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

QOSIF_LIB_URL = "git://github.com/renesas-rcar/qos_lib.git;protocol=https"
BRANCH = "rcar-gen3"
SRCREV = "761a765901ab9e009f6503904276c62fcf5cbb9b"

SRC_URI = "${QOSIF_LIB_URL};branch=${BRANCH}"

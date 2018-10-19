LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=30a99e0d36a3da1f5cf93c070ad7888a \
"

require include/rcar-gen3-modules-common.inc

VSPMIF_LIB_URL = "git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH = "rcar_gen3"
SRCREV = "a2d67ccc2080a5fdc63dd0f31cf7366792c2e113"

SRC_URI = "${VSPMIF_LIB_URL};branch=${BRANCH}"

SRC_URI_append = " \
    file://0001-rcar_gen3-To-correspond-boards-that-do-not-support-F.patch \
"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

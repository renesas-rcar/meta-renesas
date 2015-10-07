LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

VSPMIF_LIB_URL = " \
    git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH = "rcar_gen3"
SRCREV = "6d54b345bfa3308aeedcdce66e84deb0fd8a433a"

SRC_URI = "${VSPMIF_LIB_URL};protocol=git;branch=${BRANCH}"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

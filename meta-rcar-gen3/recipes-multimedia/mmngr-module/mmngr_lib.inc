# Common path of MMNGR and MMNGRBUF module
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
    file://${WORKDIR}/git/COPYING.MIT;md5=30a99e0d36a3da1f5cf93c070ad7888a"

MMNGR_LIB_URI = "git://github.com/renesas-rcar/mmngr_lib.git"

SRC_URI = "${MMNGR_LIB_URI};branch=rcar_gen3;protocol=https"
SRCREV = "0322548e54b45a064c9cecea29018ef50cdb8423"

# Get Wordsize of test app and change their names later to avoid override
WS:aarch64 = ""
WS:virtclass-multilib-lib32 = "32"

# These modules are machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

inherit autotools

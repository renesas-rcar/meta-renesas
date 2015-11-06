# Common path of MMNGR and MMNGRBUF module
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
    file://${WORKDIR}/git/MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c"

MMNGR_LIB_URI = "git://github.com/renesas-rcar/mmngr_lib.git"

SRC_URI = "${MMNGR_LIB_URI};protocol=git;branch=rcar_gen3"
SRCREV = "035392a2f1d288dc91054ee91f3d8683a8bce1aa"

# Get Wordsize of test app and change their names later to avoid override
WS_aarch64 = ""
WS_virtclass-multilib-lib32 = "32"

# These modules are machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools

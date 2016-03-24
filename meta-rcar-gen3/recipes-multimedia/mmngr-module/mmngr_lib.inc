# Common path of MMNGR and MMNGRBUF module
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
    file://${WORKDIR}/git/MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c"

MMNGR_LIB_URI = "git://github.com/renesas-rcar/mmngr_lib.git"

SRC_URI = "${MMNGR_LIB_URI};branch=rcar_gen3"
SRCREV = "debd19918117413d5790dbf41017e966deb92cc1"

# Get Wordsize of test app and change their names later to avoid override
WS_aarch64 = ""
WS_virtclass-multilib-lib32 = "32"

# These modules are machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools

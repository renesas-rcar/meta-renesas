# Common path of MMNGR and MMNGRBUF module
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
    file://${WORKDIR}/git/MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c"

MMNGR_LIB_URI = "git://github.com/renesas-rcar/mmngr_lib.git"

SRC_URI = "${MMNGR_LIB_URI};protocol=git;branch=rcar_gen3"
SRCREV = "23c6e15f42b86e4e673bbcd6848045ec3fd42e53"

# Get Wordsize of test app and change their names later to avoid override
WS_aarch64 = ""
WS_virtclass-multilib-lib32 = "32"

# These modules are machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools

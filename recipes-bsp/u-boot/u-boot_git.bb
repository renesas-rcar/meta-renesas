#inherit renesas-boot
require recipes-bsp/u-boot/u-boot.inc

PR = "r1"
PV = "v2012.10"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

# Virtex-5 src location
KBRANCH = "master"
SRC_URI = "git://git.denx.de/u-boot.git;branch=${KBRANCH};protocol=git"
SRCREV = "6528ff0109d81c1f21d20f9f1370782bccf87bcb"

S = "${WORKDIR}/git"

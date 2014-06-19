require u-boot.inc

PR = "r2"
PV = "v2013.01.01"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

KBRANCH = "master"
SRC_URI = "git://git.denx.de/u-boot.git;branch=${KBRANCH};protocol=git"
SRCREV = "e8ae0fa5edd152b2b29c470b88429be4cdcd2c46"

S = "${WORKDIR}/git"

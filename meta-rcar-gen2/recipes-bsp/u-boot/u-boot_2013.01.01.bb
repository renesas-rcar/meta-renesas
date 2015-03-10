require u-boot.inc

# This is needs to be validated among supported BSP's before we can
# make it default
DEFAULT_PREFERENCE = "-1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PV = "v2013.01.01+git${SRCPV}"

SRCREV = "b653737dfca271d2f3d96cf02c67cabef6112dab"
SRC_URI = "git://git.denx.de/u-boot-sh.git;branch=renesas/bsp/rcar-gen2-1.9.2;protocol=git"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager)"

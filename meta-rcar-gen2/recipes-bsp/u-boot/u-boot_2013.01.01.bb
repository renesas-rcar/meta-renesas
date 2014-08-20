require u-boot.inc

# This is needs to be validated among supported BSP's before we can
# make it default
DEFAULT_PREFERENCE = "-1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PV = "v2013.01.01+git${SRCPV}"

SRCREV = "1d8648f9a950e7241605ddf98b13f64955b39514"
SRC_URI = "git://git.denx.de/u-boot-sh.git;branch=renesas/bsp/rcar-gen2-1.6.0;protocol=git"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager)"

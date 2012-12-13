DESCRIPTION = "Linux mainline kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
include linux-rmobile.inc

PV = "3.7"
PR = "r0"

SRCREV = "ed23ec4f0a510528e0ffe415f9394107418ae854"

KBRANCH = "master"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=${KBRANCH} \
	   file://defconfig"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(r8a7740|sh73a0)"

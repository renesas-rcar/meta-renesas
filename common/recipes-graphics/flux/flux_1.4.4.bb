SUMMARY = "flux is an interface description language used by DirectFB"
HOMEPAGE = "http://directfb.org"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/fluxcomp.cpp;beginline=13;endline=32;md5=eb9874d4eb31887b9bc6b14fefff731c"

PR = "r0"

SRCREV = "d3086b8e39aaddd5302de6ce16cdc300ffd3ac75"
SRC_URI = "git://git.directfb.org/git/directfb/core/flux.git;protocol=git"

PR = "r0"
PV = "1.4.4+git${SRCPV}"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

BBCLASSEXTEND = "native"

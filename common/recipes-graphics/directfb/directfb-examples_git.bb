DEFAULT_PREFERENCE = "-1"
DESCRIPTION = "The DirectFB-examples package contains a set of simple DirectFB \
      applications that can be used to test and demonstrate various DirectFB \
      features"
DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "MIT"
RV = "1.7-0+git"
PR = "r0"

SRC_URI = " \
           git://git.directfb.org/git/directfb/extras/DirectFB-examples.git;protocol=git \
          "
SRCREV = "61ddf7dc02ee28d8cab20ad482f37c2ad4390835"

LIC_FILES_CHKSUM = "file://COPYING;md5=ecf6fd2b19915afc4da56043926ca18f"

S = "${WORKDIR}/git"

inherit autotools

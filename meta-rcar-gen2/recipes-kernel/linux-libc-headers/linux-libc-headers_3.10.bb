require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

KORG_ARCHIVE_COMPRESSION = "xz"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager)"
PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "537c15db45d1cdf8cb34b05dd62b2cc5150a80f9"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.9.0 \
    file://scripts-Makefile.headersinst-install-headers-from-sc.patch"
S = "${WORKDIR}/git"

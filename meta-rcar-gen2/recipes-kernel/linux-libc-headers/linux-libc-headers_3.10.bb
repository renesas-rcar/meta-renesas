require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

KORG_ARCHIVE_COMPRESSION = "xz"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager)"
PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "2f3d5c5eec68c030cce60da6398d3f5bae684e63"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.6.0"
S = "${WORKDIR}/git"

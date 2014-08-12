require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

KORG_ARCHIVE_COMPRESSION = "xz"
COMPATIBLE_MACHINE = "(alt|koelsch|lager)"
PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "d18a39b9b72ea70493f3e459d54182a810e6f033"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.5.0"
S = "${WORKDIR}/git"

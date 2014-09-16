require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

KORG_ARCHIVE_COMPRESSION = "xz"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager)"
PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "e130acb4cfd76e9b01655a6a36cdbd7a26259cd4"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.6.1"
S = "${WORKDIR}/git"

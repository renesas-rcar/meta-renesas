require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BACKPORT_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/geert/renesas-drivers.git"

SRCREV = "d7ba9d1e0b037c24ee7f278fdcf9c44f80f3dbe7"

SRC_URI = "${RENESAS_BACKPORT_URL};protocol=git;nobranch=1"

S = "${WORKDIR}/git"

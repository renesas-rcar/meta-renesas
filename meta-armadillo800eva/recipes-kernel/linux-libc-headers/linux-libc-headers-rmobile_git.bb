require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "r0"

PROVIDES = "linux-libc-headers"
RPROVIDES_${PN}-dev = "linux-libc-headers-dev"
RPROVIDES_${PN}-dbg = "linux-libc-headers-dbg"

SRCREV = "ed23ec4f0a510528e0ffe415f9394107418ae854"

KBRANCH = "master"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=${KBRANCH} \
	  "

S = "${WORKDIR}/git"

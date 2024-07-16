require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

SRC_URI = "git://github.com/torvalds/linux.git;branch=master;protocol=https"
SRCREV = "830b3c68c1fb1e9176028d02ef86f3cf76aa2476"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

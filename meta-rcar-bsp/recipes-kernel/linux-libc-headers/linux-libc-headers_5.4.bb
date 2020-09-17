require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4/rcar-4.0.1.rc1"
SRCREV = "7f6e5e961032c3a4395650197633f332d3efaef9"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Fix missing "WITH Linux-syscall-note" for SPDX-License-Identifier
SRC_URI_append = " \
    file://0001-renesas_uioctl-Fix-missing-WITH-Linux-syscall-note.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

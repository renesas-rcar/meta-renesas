require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.14/rcar-3.6.0"
SRCREV = "cdbdfa1452a86607db4d43914f4953ac811d2c56"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Disable CPUIdle support for CA53 in M3ULCB ES1.0
SRC_URI_append = " \
    file://0001-arm64-dts-r8a7796-m3ulcb-Disable-CPUIdle-support-for.patch \
"

S = "${WORKDIR}/git"

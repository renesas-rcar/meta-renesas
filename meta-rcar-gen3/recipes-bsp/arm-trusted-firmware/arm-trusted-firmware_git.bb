DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.md;md5=829bdeb34c1d9044f393d5a16c068371"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/git"

BRANCH = "master"
SRC_URI = \
    "git://github.com/ARM-software/arm-trusted-firmware.git;protocol=https;branch=${BRANCH}"
SRCREV = "e234ba038b0b997bd4325dad384deab5863babdd"

SRC_URI += " \
    file://0001-BL2-BL31-Sep-7-local-release.patch \
"

PV = "v1.1+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "salvator-x"
PLATFORM = "rcar"
ATFW_OPT = \
    "RCAR_QOS_TYPE=3 RCAR_DRAM_SPLIT=1"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure() {
    :
}

do_compile() {
    oe_runmake bl2 bl31 PLAT=${PLATFORM} ${ATFW_OPT}
}

do_install() {
    :
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2/bl2.elf ${DEPLOYDIR}/bl2-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.bin ${DEPLOYDIR}/bl2-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.srec ${DEPLOYDIR}/bl2-${MACHINE}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/bl31-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}.srec
}
addtask deploy before do_build after do_compile

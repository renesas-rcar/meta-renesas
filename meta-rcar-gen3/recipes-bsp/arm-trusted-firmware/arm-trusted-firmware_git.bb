DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.md;md5=829bdeb34c1d9044f393d5a16c068371"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy
require include/omx-options.inc

S = "${WORKDIR}/git"

BRANCH = "rcar_gen3"
SRC_URI = \
    "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH}"
SRC_URI_append = \
    " file://0001-Add-RCAR_BKUP_ENABLE-flag.patch \
      file://0002-plat-rcar-bl2_secure_setting-Enable-access-to-RPC.patch"
SRCREV = "3ad02acfc46bfbebb4a5986b250b31a1f871d2b1"
SRC_URI_append_m3ulcb = \
    " file://0003-IPL-ddrinit-rev-0.20rc8.patch \
"

PV = "v1.1+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(salvator-x|h3ulcb|m3ulcb)"
PLATFORM = "rcar"
ATFW_OPT_LOSSY = "${@base_conditional("USE_MULTIMEDIA", "1", "RCAR_LOSSY_ENABLE=1", "", d)}"
ATFW_OPT_BKUP_h3ulcb = "RCAR_BKUP_ENABLE=0"
ATFW_OPT_BKUP_m3ulcb = "RCAR_BKUP_ENABLE=0"
ATFW_OPT_r8a7795 = " \
    LSI=H3 RCAR_DRAM_SPLIT=1 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BKUP} \
    ${@base_conditional("CA57CA53BOOT", "1", " PSCI_DISABLE_BIGLITTLE_IN_CA57BOOT=0", "", d)} \
"
ATFW_OPT_r8a7796 = " \
    LSI=M3 RCAR_DRAM_SPLIT=2 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BKUP} \
    ${@base_conditional("CA57CA53BOOT", "1", " PSCI_DISABLE_BIGLITTLE_IN_CA57BOOT=0", "", d)} \
"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake bl2 bl31 dummytool PLAT=${PLATFORM} ${ATFW_OPT}
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy IPL to deploy folder
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2/bl2.elf ${DEPLOYDIR}/bl2-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.bin ${DEPLOYDIR}/bl2-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.srec ${DEPLOYDIR}/bl2-${MACHINE}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/bl31-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}.srec
    install -m 0644 ${S}/tools/dummy_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0.srec
    install -m 0644 ${S}/tools/dummy_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6.srec
}
addtask deploy before do_build after do_compile

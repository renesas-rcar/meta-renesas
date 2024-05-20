CRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM:rcar-v3x = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"
LIC_FILES_CHKSUM:rcar-v4x = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH};protocol=https"

BRANCH:rcar-v3x = "rcar_gen3_v1.5_v3x"
SRCREV:rcar-v3x = "8995b18869fed71e4a9332d9273704774f98ec41"

BRANCH:rcar-v4x = "rcar_gen4_v2.7_v4x"
SRCREV:rcar-v4x = "b88b3f5a81b57669e6b0444fd7eaf19f4a039762"

PV:rcar-v3x = "v1.5+renesas+git${SRCPV}"
PV:rcar-v4x = "v2.7+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(condor|eagle|whitehawk|grayhawk)"

PLATFORM:rcar-v3x = "rcar"
PLATFORM:rcar-v4x = "rcar_gen4"

SOC:r8a77980 = "V3H"
SOC:r8a77970 = "V3M"
SOC:r8a779g0 = "V4H"
SOC:r8a779h0 = "V4M"

ATFW_OPT:rcar-v3x = "LSI=${SOC} RCAR_DRAM_SPLIT=0 RCAR_LOSSY_ENABLE=0 PMIC_ROHM_BD9571=0 \
RCAR_SYSTEM_SUSPEND=0 SPD=none RCAR_SECURE_BOOT=0 LIFEC_DBSC_PROTECT_ENABLE=0 MBEDTLS_COMMON_MK=1"

ATFW_OPT:rcar-v4x = "rcar_srecord ARCH=aarch64 LSI=${SOC} MBEDTLS_COMMON_MK=1 CTX_INCLUDE_AARCH32_REGS=0 \
LOG_LEVEL=20 DEBUG=0 ENABLE_ASSERTIONS=1 RCAR_RPC_HYPERFLASH_LOCKED=0 PTP_NONSECURE_ACCESS=1"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake bl31 PLAT=${PLATFORM} ${ATFW_OPT}
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}.srec
}

addtask deploy before do_build after do_compile


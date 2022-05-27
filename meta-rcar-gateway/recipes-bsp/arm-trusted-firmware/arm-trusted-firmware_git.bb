DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/git"

BRANCH = "rcar-s4_v2.5"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH}"
SRCREV = "5b0edfa45acfd8e14618cd324b62e49e33dcc40b"

SRC_URI_append = "\
        file://0001-drivers-gicv3-Fix-setting-E-PPI-SGI-priorities.patch \
        file://0002-rcar_gen4-plat-BL31-Set-the-interrupt-priority-unifo.patch \
"

PV = "v2.5+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(spider)"
PLATFORM = "rcar_gen4"
ATFW_OPT_r8a779f0 = "LSI=S4 CTX_INCLUDE_AARCH32_REGS=0 LOG_LEVEL=10 DEBUG=0"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake bl31 rcar_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT}
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

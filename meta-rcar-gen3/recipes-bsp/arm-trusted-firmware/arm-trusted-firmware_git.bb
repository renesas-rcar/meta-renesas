DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy
require include/multimedia-control.inc

S = "${WORKDIR}/git"

BRANCH = "rcar_gen3_v2.5"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH};protocol=https"
SRCREV = "268df1d862bc564458aee43f8ec9e71d7ec794bc"

PV = "v2.5+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"
PLATFORM = "rcar"
ATFW_OPT_LOSSY = "${@oe.utils.conditional("USE_MULTIMEDIA", "1", "RCAR_LOSSY_ENABLE=1", "", d)}"
ATFW_OPT:r8a7795 = "LSI=H3 RCAR_DRAM_SPLIT=1 RCAR_DRAM_LPDDR4_MEMCONF=0 ${ATFW_OPT_LOSSY}"
ATFW_OPT:r8a7796 = "LSI=M3 RCAR_DRAM_SPLIT=2  ${ATFW_OPT_LOSSY}"
ATFW_OPT:r8a77965 = "LSI=M3N ${ATFW_OPT_LOSSY}"
ATFW_OPT:r8a77990 = "LSI=E3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RCAR_DRAM_DDR3L_MEMCONF=0 RCAR_DRAM_DDR3L_MEMDUAL=0"
ATFW_OPT:r8a77995 = "LSI=D3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 DEBUG=0"
ATFW_OPT:append:ulcb = " RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0"

# IPL build options for H3/E3/H3ULCB
EXTRA_ATFW_OPT ?= ""
EXTRA_ATFW_CONF ?= ""

H3ULCB[4x2g] = "LSI=H3 RCAR_DRAM_SPLIT=1 RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0"
H3[2x2g] = "LSI=H3 RCAR_DRAM_SPLIT=2 RCAR_DRAM_CHANNEL=5"
H3[4x2g] = "LSI=H3 RCAR_DRAM_SPLIT=1"
E3[4d] = "LSI=E3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RCAR_DRAM_DDR3L_MEMCONF=1 RCAR_DRAM_DDR3L_MEMDUAL=1"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake bl2 bl31 rcar_layout_tool rcar_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT}
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
    install -m 0644 ${S}/tools/renesas/rcar_layout_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0.srec
    install -m 0644 ${S}/tools/renesas/rcar_layout_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6.srec
}

do_ipl_opt_compile () {
    oe_runmake distclean
    oe_runmake bl2 bl31 rcar_layout_tool rcar_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${EXTRA_ATFW_OPT} ${ATFW_OPT_LOSSY}
}

do_ipl_opt_deploy () {
    install -d ${DEPLOY_DIR_IMAGE}

    # Copy IPL to deploy folder
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2/bl2.elf ${DEPLOY_DIR_IMAGE}/bl2-${MACHINE}-${EXTRA_ATFW_CONF}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.bin ${DEPLOY_DIR_IMAGE}/bl2-${MACHINE}-${EXTRA_ATFW_CONF}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.srec ${DEPLOY_DIR_IMAGE}/bl2-${MACHINE}-${EXTRA_ATFW_CONF}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOY_DIR_IMAGE}/bl31-${MACHINE}-${EXTRA_ATFW_CONF}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOY_DIR_IMAGE}/bl31-${MACHINE}-${EXTRA_ATFW_CONF}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOY_DIR_IMAGE}/bl31-${MACHINE}-${EXTRA_ATFW_CONF}.srec
    install -m 0644 ${S}/tools/renesas/rcar_layout_create/bootparam_sa0.srec ${DEPLOY_DIR_IMAGE}/bootparam_sa0-${EXTRA_ATFW_CONF}.srec
    install -m 0644 ${S}/tools/renesas/rcar_layout_create/cert_header_sa6.srec ${DEPLOY_DIR_IMAGE}/cert_header_sa6-${EXTRA_ATFW_CONF}.srec
}

def do_extra_aft_build (d, board):
    extra_aft_confs_dict = d.getVarFlags(board)
    extra_aft_confs_list = list(extra_aft_confs_dict.keys())

    for atf_conf in extra_aft_confs_list:
        d.setVar('EXTRA_ATFW_CONF', atf_conf)
        d.setVar('EXTRA_ATFW_OPT', extra_aft_confs_dict[atf_conf])
        bb.build.exec_func('do_ipl_opt_compile', d)
        bb.build.exec_func('do_ipl_opt_deploy', d)


# For IPL compile options for H3/H3ULCB (SoC: r8a7795), E3 (SoC: r8a7790)
python do_extra_ipl_opt () {
    soc = d.getVar('SOC_FAMILY')
    soc = soc.split(':')[1]
    machine = d.getVar('MACHINE_ARCH')

    if soc == "r8a7795":
        if machine != "h3ulcb":
            do_extra_aft_build(d, "H3")
        else:
            do_extra_aft_build(d, "H3ULCB")

    if soc == "r8a77990":
        do_extra_aft_build(d, "E3")
}

do_ipl_opt_compile[dirs] = "${B}"
do_ipl_opt_deploy[dirs] = "${B}"

addtask deploy before do_build after do_compile
addtask extra_ipl_opt after do_configure before do_compile

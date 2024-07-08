DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

require include/multimedia-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

PV = "v2.9+renesas+git${SRCPV}"

BRANCH = "rcar-gen3_v2.9"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH};protocol=https"
SRCREV = "9cdb21f75157fc82e8ca104aa21c4ab722383b04"

SRC_URI += " file://0001-Makefile-Disable-linker-warning.patch"

S = "${WORKDIR}/git"

PLATFORM = "rcar"

ATFW_OPT ?= ""
ATFW_CONF ?= ""

# IPL build options for H3/E3/M3/M3N/D3
ATFW_OPT_LOSSY = "${@oe.utils.conditional("USE_MULTIMEDIA", "1", "RCAR_LOSSY_ENABLE=1", "", d)}"
ATFW_OPT_BOOTMODE = "${@oe.utils.conditional("USE_EMMC_BOOTMODE", "1", "RCAR_SA6_TYPE=1", "", d)}"

h3ulcb_r8a7795[4x1g]         = "LSI=H3 RCAR_DRAM_SPLIT=1 RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0 RCAR_DRAM_LPDDR4_MEMCONF=0 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"
h3ulcb_r8a7795[4x2g]         = "LSI=H3 RCAR_DRAM_SPLIT=1 RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"

m3ulcb_r8a7796[default]      = "LSI=M3 RCAR_DRAM_SPLIT=2 RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"

m3nulcb_r8a77965[default]    = "LSI=M3N RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"

ebisu_r8a77990[default]      = "LSI=E3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RCAR_DRAM_DDR3L_MEMCONF=0 RCAR_DRAM_DDR3L_MEMDUAL=0 ${ATFW_OPT_BOOTMODE}"
ebisu_r8a77990[4d]           = "LSI=E3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RCAR_DRAM_DDR3L_MEMCONF=1 RCAR_DRAM_DDR3L_MEMDUAL=1 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"

draak_r8a77995[default]      = "LSI=D3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 DEBUG=0 ${ATFW_OPT_BOOTMODE}"

salvator_x_r8a7795[4x1g]     = "LSI=H3 RCAR_DRAM_SPLIT=1 RCAR_DRAM_LPDDR4_MEMCONF=0 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"
salvator_x_r8a7795[2x2g]     = "LSI=H3 RCAR_DRAM_SPLIT=2 RCAR_DRAM_CHANNEL=5 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"
salvator_x_r8a7795[4x2g]     = "LSI=H3 RCAR_DRAM_SPLIT=1 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"

salvator_x_r8a7796[default]  = "LSI=M3 RCAR_DRAM_SPLIT=2 ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"

salvator_x_r8a77965[default] = "LSI=M3N ${ATFW_OPT_LOSSY} ${ATFW_OPT_BOOTMODE}"


# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

# do_install() nothing
do_install[noexec] = "1"

do_ipl_compile () {
    oe_runmake distclean
    oe_runmake clean_layout_tool clean_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT}
    oe_runmake bl2 bl31 rcar_layout_tool rcar_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT}

    # Create ${S}/release folder to store output for compile tasks
    install -d ${S}/release

    # Move to ${S}/release and rename
    install ${S}/build/${PLATFORM}/release/bl2/bl2.elf                  ${S}/release/bl2-${MACHINE}${ATFW_CONF}.elf
    install ${S}/build/${PLATFORM}/release/bl2.bin                      ${S}/release/bl2-${MACHINE}${ATFW_CONF}.bin
    install ${S}/build/${PLATFORM}/release/bl2.srec                     ${S}/release/bl2-${MACHINE}${ATFW_CONF}.srec
    install ${S}/build/${PLATFORM}/release/bl31/bl31.elf                ${S}/release/bl31-${MACHINE}${ATFW_CONF}.elf
    install ${S}/build/${PLATFORM}/release/bl31.bin                     ${S}/release/bl31-${MACHINE}${ATFW_CONF}.bin
    install ${S}/build/${PLATFORM}/release/bl31.srec                    ${S}/release/bl31-${MACHINE}${ATFW_CONF}.srec
    install ${S}/tools/renesas/rcar_layout_create/bootparam_sa0.srec    ${S}/release/bootparam_sa0${ATFW_CONF}.srec
    install ${S}/tools/renesas/rcar_layout_create/cert_header_sa6.srec  ${S}/release/cert_header_sa6${ATFW_CONF}.srec
}

python do_compile () {
    soc = d.getVar('SOC_FAMILY')
    soc = soc.split(':')[1]
    machine = d.getVar('MACHINE_ARCH')
    confs_dict = d.getVarFlags(machine + "_" + soc)
    confs_list = list(confs_dict.keys())

    for conf in confs_list:
        d.setVar('ATFW_OPT', confs_dict[conf])
        if conf == "default":
            d.setVar('ATFW_CONF', "")
        else:
            d.setVar('ATFW_CONF', "-" + conf)
        bb.build.exec_func('do_ipl_compile', d)
}

do_deploy () {
    # Copy binary files to deploy directory
    install -m 0644 ${S}/release/*.elf  ${DEPLOYDIR}
    install -m 0644 ${S}/release/*.bin  ${DEPLOYDIR}
    install -m 0644 ${S}/release/*.srec ${DEPLOYDIR}
}

addtask deploy after do_compile

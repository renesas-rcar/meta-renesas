# This function is to generate IPL binaries for H3/H3ULCB (SoC: r8a7795), E3 (SoC: r8a7790)

# IPL build options for H3/E3/H3ULCB
EXTRA_ATFW_OPT ?= ""
EXTRA_ATFW_CONF ?= ""

do_ipl_opt_compile () {
    oe_runmake distclean
    oe_runmake bl2 bl31 dummytool PLAT=${PLATFORM} ${EXTRA_ATFW_OPT} ${ATFW_OPT_LOSSY}
}

do_ipl_opt_deploy () {
    install -d ${DEPLOYDIR}

    # Copy IPL to deploy folder
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2/bl2.elf ${DEPLOYDIR}/bl2-${MACHINE}-${EXTRA_ATFW_CONF}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.bin ${DEPLOYDIR}/bl2-${MACHINE}-${EXTRA_ATFW_CONF}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.srec ${DEPLOYDIR}/bl2-${MACHINE}-${EXTRA_ATFW_CONF}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/bl31-${MACHINE}-${EXTRA_ATFW_CONF}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}-${EXTRA_ATFW_CONF}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}-${EXTRA_ATFW_CONF}.srec
    install -m 0644 ${S}/tools/dummy_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0-${EXTRA_ATFW_CONF}.srec
    install -m 0644 ${S}/tools/dummy_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6-${EXTRA_ATFW_CONF}.srec
}

# For IPL compile options for H3/H3ULCB (SoC: r8a7795), E3 (SoC: r8a7790)
python do_extra_ipl_opt() {
    soc = d.getVar('SOC_FAMILY')
    soc = soc.split(':')[1]
    machine = d.getVar('MACHINE_ARCH')

    if soc == "r8a7795":
        # For H3 SiP DDR 4GiB (2GiB x 2ch)
        if machine != "h3ulcb":
            d.setVar('EXTRA_ATFW_CONF', '2x2g')
            d.setVar('EXTRA_ATFW_OPT', ' LSI=H3 RCAR_DRAM_SPLIT=2 RCAR_DRAM_CHANNEL=5 ')
            bb.build.exec_func('do_ipl_opt_compile', d)
            bb.build.exec_func('do_ipl_opt_deploy', d)

        # For H3/H3ULCB SiP DDR 8GiB (2GiB x 4ch)
        d.setVar('EXTRA_ATFW_CONF', '4x2g')
        d.setVar('EXTRA_ATFW_OPT', ' LSI=H3 RCAR_DRAM_SPLIT=1 ')
        d.setVar('EXTRA_ATFW_OPT_append_ulcb', ' RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0 ')
        bb.build.exec_func('do_ipl_opt_compile', d)
        bb.build.exec_func('do_ipl_opt_deploy', d)

    # For E3 SiP DDR 2GiB
    if soc == "r8a77990":
        d.setVar('EXTRA_ATFW_CONF', '4d')
        d.setVar('EXTRA_ATFW_OPT', ' LSI=E3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RCAR_DRAM_DDR3L_MEMCONF=1 RCAR_DRAM_DDR3L_MEMDUAL=1 ')
        bb.build.exec_func('do_ipl_opt_compile', d)
        bb.build.exec_func('do_ipl_opt_deploy', d)
}

do_ipl_opt_compile[dirs] = "${B}"
do_ipl_opt_deploy[dirs] = "${B}"

addtask extra_ipl_opt after do_configure before do_compile

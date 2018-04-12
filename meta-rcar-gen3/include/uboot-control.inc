# This function is to add more U-Boot options to build with H3 (SoC: r8a7795)

def get_uboot_config_opt(d):
    option = d.getVar('H3_OPTION')
    if option == "1":
        d.setVar('UBOOT_CONFIG_OPT',' KCFLAGS+=-DRCAR_DRAM_MAP4_2 KAFLAGS+=-DRCAR_DRAM_MAP4_2')
    elif option == "2":
        d.setVar('UBOOT_CONFIG_OPT',' KCFLAGS+=-DRCAR_DRAM_MAP2_2 KAFLAGS+=-DRCAR_DRAM_MAP2_2')
    else:
        d.setVar('UBOOT_CONFIG_OPT','')
    return d.getVar('UBOOT_CONFIG_OPT')

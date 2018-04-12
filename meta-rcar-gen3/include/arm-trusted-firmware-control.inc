# This function is to add more IPL options to build with H3 (SoC: r8a7795)

def get_ipl_config_opt(d):
    option = d.getVar('H3_OPTION')
    if option == "1":
        d.setVar('IPL_CONFIG_OPT','RCAR_DRAM_SPLIT=1')
    elif option == "2":
        d.setVar('IPL_CONFIG_OPT','RCAR_DRAM_SPLIT=2 RCAR_DRAM_CHANNEL=5')
    else:
        d.setVar('IPL_CONFIG_OPT','RCAR_DRAM_SPLIT=1 RCAR_DRAM_LPDDR4_MEMCONF=0')
    return d.getVar('IPL_CONFIG_OPT')

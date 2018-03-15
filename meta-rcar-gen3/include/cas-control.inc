SOC="${@d.getVar('SOC_FAMILY').split(':')[1]}"
CAS="${@'1' if 'cas' in '${MACHINE_FEATURES}' else '0'}"

# USE_CAS for H3 (SoC: r8a7795) or M3 (SoC: r8a7796)
USE_CAS="${@'1' if '${CAS}' == '1' and ('${SOC}' == 'r8a7795' or '${SOC}' == 'r8a7796') else '0'}"

# Trap incorrect CAS setting for M3N/E3
WARN="${@'1' if '${CAS}' == '1' and '${USE_CAS}' == '0' else '0'}"

# This function is to show a warning message when enabling CAS feature on M3N/E3
python do_cas_checking() {
    warn=d.getVar('WARN')
    if warn == "1" :
        bb.warn("CAS only supports H3 (SoC: r8a7795), M3 (SoC: r8a7796), this feature will be disabled automatically")
}

addtask cas_checking before do_configure after do_patch

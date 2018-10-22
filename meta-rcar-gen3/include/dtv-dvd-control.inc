# USE_DTV for ISDB-T DTV Software Package
USE_DTV = "${@'1' if 'dtv' in '${DISTRO_FEATURES}' else '0'}"

# USE_DVD for DVD Core-Middleware for Linux
USE_DVD = "${@'1' if 'dvd' in '${DISTRO_FEATURES}' else '0'}"

# USE_DVD_ENCRYPTION_LIB for DVD Encryption Library for Linux
USE_DVD_ENCRYPTION_LIB = "${@'1' if 'dvd_encryption_library' in '${DISTRO_FEATURES}' else '0'}"

# This function to prevent build DTV feature with E3
python do_dtv_checking () {
    soc = d.getVar('SOC_FAMILY')
    soc = soc.split(':')[1]
    use_dtv = d.getVar('USE_DTV')
    if use_dtv == "1" and soc == "r8a77990":
        bb.fatal("E3 (SoC: r8a77990) does not support SSP, please disable DTV feature in your local configuration")
}

addtask dtv_checking before do_configure after do_patch

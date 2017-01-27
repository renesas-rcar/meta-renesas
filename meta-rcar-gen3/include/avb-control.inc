# USE_AVB for AVB Software Package for Linux
USE_AVB = "${@'1' if 'avb' in '${DISTRO_FEATURES}' else '0'}"

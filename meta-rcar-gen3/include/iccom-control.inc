# USE_ICCOM for Linux ICCOM driver, Linux ICCOM library
USE_ICCOM = "${@'1' if 'iccom' in '${DISTRO_FEATURES}' else '0'}"

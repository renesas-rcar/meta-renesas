USE_DTV = "${@'1' if 'dtv' in '${MACHINE_FEATURES}' else '0'}"
USE_MULTIMEDIA = "${@'1' if 'multimedia' in '${MACHINE_FEATURES}' or '${USE_DTV}' == '1' else '0'}"
USE_MULTIMEDIA_TEST = "${@'1' if 'mm-test' in '${DISTRO_FEATURES}' and '${USE_MULTIMEDIA}'  == '1' else '0'}"

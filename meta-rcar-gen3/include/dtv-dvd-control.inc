# USE_DTV for ISDB-T DTV Software Package
USE_DTV = "${@'1' if 'dtv' in '${DISTRO_FEATURES}' else '0'}"

# USE_DVD for DVD Core-Middleware for Linux
USE_DVD = "${@'1' if 'dvd' in '${DISTRO_FEATURES}' else '0'}"

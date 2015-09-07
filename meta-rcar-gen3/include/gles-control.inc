USE_GLES = "${@'1' if 'gsx' in '${MACHINE_FEATURES}' else '0'}"
USE_WAYLAND = "${@'1' if 'wayland' in '${DISTRO_FEATURES}' else '0'}"
USE_GLES_WAYLAND = \
    "${@'1' if '${USE_GLES}' == '1' and '${USE_WAYLAND}' == '1' else '0'}"

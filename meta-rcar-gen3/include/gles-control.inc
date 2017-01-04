USE_GLES = "${@'1' if 'gsx' in '${MACHINE_FEATURES}' else '0'}"
USE_GLES_WAYLAND = \
    "${@'1' if '${USE_GLES}' == '1' and 'wayland' in '${DISTRO_FEATURES}' else '0'}"
USE_WAYLAND_IVI_SHELL = \
    "${@'1' if 'ivi-shell' in '${DISTRO_FEATURES}' and \
    'wayland' in '${DISTRO_FEATURES}' else '0'}"

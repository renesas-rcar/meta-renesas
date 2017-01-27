# USE_MULTIMEDIA for Renesas Multimedia package
USE_MULTIMEDIA = "${@'1' if 'multimedia' in '${MACHINE_FEATURES}' else '0'}"
USE_MULTIMEDIA_TP = "${@'1' if 'mm-test' in '${DISTRO_FEATURES}' else '0'}"

# For wayland
USE_WAYLAND = "${@'1' if 'wayland' in '${DISTRO_FEATURES}' else '0'}"

# USE_V4L2_RENDERER for weston v4l2-renderer
USE_V4L2_RENDERER = "${@'1' if 'v4l2-renderer' in '${DISTRO_FEATURES}' else '0'}"

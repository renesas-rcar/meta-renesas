MESATARGET = "${@base_contains('DISTRO_FEATURES', 'wayland', 'wayland', 'x11', d)}"
include mesa-${MESATARGET}.inc

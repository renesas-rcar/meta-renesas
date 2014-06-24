PACKAGECONFIG ??= " \
    ${@base_contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
    ivorbis ogg theora vorbis \
    "

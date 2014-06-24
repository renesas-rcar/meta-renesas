PACKAGECONFIG ??= " \
    ${@base_contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)} \
    cairo flac gdk-pixbuf jpeg libpng soup speex taglib \
    "

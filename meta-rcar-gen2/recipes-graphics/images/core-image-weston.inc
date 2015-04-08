include core-image-renesas-base.inc

IMAGE_INSTALL_append_rcar-gen2 = '${@base_conditional("USE_GLES", "1", \
    " libegl libegl-dev libgbm-dev", "", d)} \
    gstreamer1.0-plugins-bad-waylandsink'

# pkg groups for oss codecs
IMAGE_INSTALL_append_lcb = ' ${@base_conditional("USE_MULTIMEDIA", "1", " \
    packagegroup-lcb-oss-codecs \
    ", "", d)}'

IMAGE_INSTALL_append_lcb = " \
    can-utils \
    libsocketcan \
"

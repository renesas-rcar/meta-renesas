include core-image-renesas-base.inc

IMAGE_FEATURES += "x11-sato ssh-server-dropbear"

IMAGE_INSTALL += "packagegroup-core-x11-sato-games"

# pkg groups for oss codecs
IMAGE_INSTALL_append_silk = ' ${@base_conditional("USE_MULTIMEDIA", "1", " \
    packagegroup-lcb-oss-codecs \
    ", "", d)}'

IMAGE_INSTALL_append_silk = " \
    can-utils \
    libsocketcan \
"

IMAGE_INSTALL_append_porter = ' ${@base_conditional("USE_MULTIMEDIA", "1", " \
    packagegroup-lcb-oss-codecs \
    ", "", d)}'

IMAGE_INSTALL_append_porter = " \
    can-utils \
    libsocketcan \
"

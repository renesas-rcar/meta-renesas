# Common Multi Media Packages (MMP)
MULTIMEDIA_PKGS = " \
    packagegroup-multimedia-kernel-modules \
    packagegroup-multimedia-libs \
"

# MMP test Packages
MULTIMEDIA_TP_PKGS = " \
    vspm-tp-user-module \
"

# Basic packages
IMAGE_INSTALL_append_rcar-gen2 = " \
    bash \
    v4l-utils \
    capture \
    i2c-tools \
    can-utils \
    libsocketcan \
    iproute2 \
    spidev-dbg \
    e2fsprogs \
    ethtool \
    pciutils \
    usbutils \
    util-linux \
    mtd-utils \
    capture \
    v4l2-fw \
    iperf \
    bonnie++ \
    lmbench \
"

IMAGE_INSTALL_append_rcar-gen2 = '${@base_conditional("USE_GLES", "1", \
    "kernel-module-gles gles-user-module", "", d)}'

# Install pkg selection
IMAGE_INSTALL_append_rcar-gen2 = " \
    ${@base_conditional("USE_MULTIMEDIA", "1", " ${MULTIMEDIA_PKGS}", "", d)} \
    ${@base_conditional("USE_MULTIMEDIA_TP", "1", " ${MULTIMEDIA_TP_PKGS}", "", d)} \
"

# Qos user packages only supports 64bit.
QOS_USER_PKGS = ""

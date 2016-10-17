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


# Qos user packages only supports 64bit.
QOS_USER_PKGS = ""

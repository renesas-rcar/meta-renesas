DESCRIPTION = "OSS packages for V3X development & testing"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

PACKAGES = " \
    packagegroup-oss \
    packagegroup-bsp-devpkg \
    packagegroup-bsp-testpkg \
    packagegroup-bsp-python3 \
    packagegroup-bsp-utest \
"

RDEPENDS_packagegroup-oss = " \
    packagegroup-bsp-devpkg \
    packagegroup-bsp-testpkg \
    packagegroup-bsp-python3 \
    packagegroup-bsp-utest \
"

# OSS packages for development
RDEPENDS_packagegroup-bsp-devpkg = " \
    cmake \
    g++ \
    gcc \
    git \
    git-perltools \
    make \
    atop \
    curl \
    devmem2 \
    gdb \
    gdbserver \
    iperf3 \
    iproute2 \
    iproute2-tc \
    linuxptp \
    nano \
    openssh \
    openssh-sftp \
    openssh-sftp-server \
    opkg \
    perf \
    procps \
    strace \
    vim \
    wget \
    ${@bb.utils.contains("IMAGE_FEATURES", "ssh-server-openssh", "", "dropbear",d)} \
"

# Various packages needed for testing
RDEPENDS_packagegroup-bsp-testpkg = " \
    bonnie++ \
    can-utils \
    e2fsprogs \
    e2fsprogs-tune2fs \
    eglibc-utils \
    ethtool \
    ldd \
    libpcap \
    libsocketcan \
    lmbench \
    mtd-utils \
    pciutils \
    rsync \
    subversion \
    usbutils \
    util-linux \
    dbench \
    dhrystone \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "non-commercial", "netperf", "", d)} \
    whetstone \
"

# Python3 packages requested by Renesas
RDEPENDS_packagegroup-bsp-python3 = " \
    python3-dbus \
    python3-nose \
    python3-numpy \
    python3-pygobject \
    python3-pyyaml \
    python3-setuptools \
"

# Utest (IMR, IMP, etc demos) related packages
RDEPENDS_packagegroup-bsp-utest = " \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-base-app \
    libdrm \
    libinput \
    libgstallocators-1.0 \
    libgstapp-1.0 \
    libyaml \
"

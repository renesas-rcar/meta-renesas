DESCRIPTION = "OSS packages for S4 development & testing"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

PACKAGES = " \
    packagegroup-oss \
    packagegroup-bsp-devpkg \
    packagegroup-bsp-testpkg \
    packagegroup-bsp-python3 \
"

RDEPENDS_packagegroup-oss = " \
    packagegroup-bsp-devpkg \
    packagegroup-bsp-testpkg \
    packagegroup-bsp-python3 \
"

# OSS packages for development
RDEPENDS_packagegroup-bsp-devpkg = " \
    cmake \
    g++ \
    gcc \
    git \
    git-perltools \
    make \
    curl \
    devmem2 \
    gdb \
    gdbserver \
    iperf3 \
    iproute2 \
    iproute2-tc \
    linuxptp \
    nano \
    openssh-sftp \
    openssh-sftp-server \
    opkg \
    perf \
    procps \
    rsync \
    wget \
    libgpiod libgpiod-tools \
    libyaml \
    i2c-tools \
    stress \
    spidev-dbg spidev-test \
    avahi-daemon \
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
    mtd-utils-jffs2 \
    pciutils \
    subversion \
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
    python3-pip \
"

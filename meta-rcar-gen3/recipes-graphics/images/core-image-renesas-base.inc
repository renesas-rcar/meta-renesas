require include/iccom-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"

# Basic packages
IMAGE_INSTALL:append = " \
    bash \
    v4l-utils \
    i2c-tools \
    coreutils \
"

# Support secure environment
IMAGE_INSTALL:append = " \
    optee-client \
"

# Add overrides for selection 32 bit or 64 bit target
OVERRIDES .= ":${TARGET_SYS}"

# Qos user packages only supports 64bit.
QOS_USER_PKGS = ""
QOS_USER_PKGS:aarch64-poky-linux = " \
    qosif-user-module \
    qosif-tp-user-module \
"

# Installation for QoS packages
IMAGE_INSTALL:append = " \
    kernel-module-qos \
    ${QOS_USER_PKGS} \
"

# Renesas Basic packages for 32bit
BASIC_32BIT_PKGS = " \
    lib32-coreutils \
    lib32-libstdc++ \
    lib32-optee-client \
"

# Installation for 32bit packages
IMAGE_INSTALL:append = " \
    ${@oe.utils.conditional("USE_32BIT_PKGS", "1", "${BASIC_32BIT_PKGS}", "", d)} \
"

# Kernel modules of ICCOM
ICCOM_KERNEL_PKGS = " \
    kernel-module-iccom \
"

# User modules of ICCOM only supports 64bit
ICCOM_USER_PKGS = ""
ICCOM_USER_PKGS:aarch64-poky-linux = " \
"

# ICCOM Packages
ICCOM_PKGS = " \
    ${ICCOM_KERNEL_PKGS} \
    ${ICCOM_USER_PKGS} \
"

# Installation for ICCOM Packages
IMAGE_INSTALL:append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", " ${ICCOM_PKGS}", "", d)} \
"

# Kernel modules support Safe Rendering
SAFE_RENDERING_KERNEL_PKGS = " \
    kernel-module-rcar-cr7-remoteproc \
    kernel-module-rcar-rvgc-drm \
    kernel-module-virtio-rpmsg-bus \
    kernel-module-rcar-taurus-can \
    kernel-module-rcar-taurus-ether \
    kernel-module-rcar-vivid \
"

IMAGE_INSTALL:append = " \
    ${@oe.utils.conditional("USE_SAFE_RENDERING", "1", "${SAFE_RENDERING_KERNEL_PKGS}", "", d)} \
"

# Initscript for setting CPUFreq governor
IMAGE_INSTALL:append = " cpufreq-initscripts"

# Kernel modules of CMEMDRV
IMAGE_INSTALL:append = " kernel-module-cmemdrv"

# Kernel module UIO
IMAGE_INSTALL:append = " kernel-module-uio-pdrv-genirq"

# OSAL package
IMAGE_INSTALL:append = " \
    ${@"osal" if "osal" in "${DISTRO_FEATURES}" else ""} \
"
# IMR package
IMAGE_INSTALL:append = " \
    ${@"imr" if "imr" in "${DISTRO_FEATURES}" else ""} \
"

#Installation for ethtool
IMAGE_INSTALL:append = " ethtool"

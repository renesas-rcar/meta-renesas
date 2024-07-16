require core-image-renesas-minimal.inc
require include/iccom-control.inc


# Initscript for setting CPUFreq governor
IMAGE_INSTALL:append = " cpufreq-initscripts"

# Kernel modules of CMEMDRV
IMAGE_INSTALL:append = " kernel-module-cmemdrv"

# Kernel module UIO
IMAGE_INSTALL:append = " kernel-module-uio-pdrv-genirq"

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


# Kernel modules of ICCOM
ICCOM_KERNEL_PKGS = " \
    kernel-module-iccom-mfis \
    kernel-module-iccom-hwspinlock-sample \
"

# User modules of ICCOM only supports 64bit
ICCOM_USER_PKGS = ""
ICCOM_USER_PKGS:aarch64-poky-linux = " \
    iccom-user-module \
    iccom-tp-user-module \
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

# OSAL package
IMAGE_INSTALL:append = " \
    ${@"osal" if "osal" in "${DISTRO_FEATURES}" else ""} \
"
# IMR package
IMAGE_INSTALL:append = " \
    ${@"imr" if "imr" in "${DISTRO_FEATURES}" else ""} \
"

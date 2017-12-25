require include/iccom-control.inc

# Basic packages
IMAGE_INSTALL_append = " \
    bash \
    v4l-utils \
    i2c-tools \
    libx11-locale \
    coreutils \
"

# Support secure environment
IMAGE_INSTALL_append = " \
    optee-client \
"

# Add overrides for selection 32 bit or 64 bit target
OVERRIDES .= ":${TARGET_SYS}"

# Qos user packages only supports 64bit.
QOS_USER_PKGS = ""
QOS_USER_PKGS_aarch64-poky-linux = " \
    qosif-user-module \
    qosif-tp-user-module \
"

# Installation for QoS packages
IMAGE_INSTALL_append = " \
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
IMAGE_INSTALL_append = " \
    ${@base_conditional("USE_32BIT_PKGS", "1", "${BASIC_32BIT_PKGS}", "", d)} \
"

# Kernel modules of ICCOM
ICCOM_KERNEL_PKGS = " \
    kernel-module-iccom-mfis \
    kernel-module-iccom-hwspinlock-sample \
"

# User modules of ICCOM only supports 64bit
ICCOM_USER_PKGS = ""
ICCOM_USER_PKGS_aarch64-poky-linux = " \
    iccom-user-module \
    iccom-tp-user-module \
"

# ICCOM Packages
ICCOM_PKGS = " \
    ${ICCOM_KERNEL_PKGS} \
    ${ICCOM_USER_PKGS} \
"

# Installation for ICCOM Packages
IMAGE_INSTALL_append = " \
    ${@base_conditional("USE_ICCOM", "1", " ${ICCOM_PKGS}", "", d)} \
"

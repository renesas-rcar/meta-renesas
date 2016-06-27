# common
inherit multilib_sdk

# Basic packages
IMAGE_INSTALL_append_rcar-gen3 = " \
    bash \
    v4l-utils \
    i2c-tools \
"

# Support secure environment
IMAGE_INSTALL_append = " \
    optee-linuxdriver \
    optee-linuxdriver-armtz \
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

# common
inherit multilib_sdk

# Basic packages
IMAGE_INSTALL_append_rcar-gen3 = " \
    bash \
    lib32-libstdc++ \
    v4l-utils \
"

# Support secure environment
IMAGE_INSTALL_append = " \
    optee-linuxdriver \
    optee-linuxdriver-armtz \
    optee-client \
    lib32-optee-client \
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

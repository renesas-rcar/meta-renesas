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

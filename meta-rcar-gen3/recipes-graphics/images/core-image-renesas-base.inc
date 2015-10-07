require ../../include/gles-control.inc

# common
inherit multilib_sdk

IMAGE_INSTALL_append_rcar-gen3 = " \
    alsa-utils alsa-tools \
    libdrm-tests \
    libdrm-kms \
    bash \
    lib32-libdrm \
    lib32-libdrm-kms \
    lib32-weston \
    lib32-libstdc++ \
    weston-bin \
"

# Add graphic driver and user libraries package for rcar-gen3
IMAGE_INSTALL_append_rcar-gen3 = \
    '${@base_conditional("USE_GLES", "1", \
    " kernel-module-gles gles-user-module \
    lib32-gles-user-module", "", d)}'

# Support secure environment
IMAGE_INSTALL_append = " optee-linuxdriver \
    optee-linuxdriver-armtz \
    optee-client"

# Install test app for MMP Module
IMAGE_INSTALL_append = "${@bb.utils.contains("DISTRO_FEATURES", "mm-test", \
    " vspmif-tp-user-module lib32-vspmif-tp-user-module", "", d)}"

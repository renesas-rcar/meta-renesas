require ../../include/gles-control.inc

# common
inherit multilib_sdk

IMAGE_INSTALL_append_rcar-gen3 = " \
   alsa-utils alsa-tools \
   libdrm-tests \
   libdrm-kms \
   bash \
"
# Add graphic driver and user libraries package for rcar-gen3
IMAGE_INSTALL_append_rcar-gen3 = \
    '${@base_conditional("USE_GLES", "1", \
    "kernel-module-gles gles-user-module", "", d)}'

# Support secure environment
IMAGE_INSTALL_append = " optee-linuxdriver optee-client"

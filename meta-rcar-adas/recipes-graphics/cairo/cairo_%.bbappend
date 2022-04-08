# khrplatform.h belongs to libegl but egl feature was not enabled
# This causes header missing when khrplatform.h in mesa is removed
# Enable egl to make the header  from gles-user-module to be installed
# to recipe sysroot
PACKAGECONFIG_append_rcar-v4x = " egl"

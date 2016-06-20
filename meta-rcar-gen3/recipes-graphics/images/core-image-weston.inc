require include/rcar-gen3-gles-control.inc
require core-image-renesas-mmp-base.inc

# Wayland basic packages
WAYLAND_BASE_PKGS = " \
    lib32-weston \
    weston-bin \
    alsa-utils \
    alsa-tools \
    libdrm-tests \
    libdrm-kms \
    lib32-libdrm \
    lib32-libdrm-kms \
"

# GFX for Wayland packages
WAYLAND_GLES_PKGS = "libegl libegl-dev libgbm-dev"

# GFX driver and library packages
GLES_PKGS = " \
    kernel-module-gles \
    gles-user-module \
    lib32-gles-user-module \
"

# Gstreamer Packages
GSTREAMER_PKGS = "packagegroup-gstreamer1.0-plugins"

# Install packages for Wayland Env
# default: Basic packages, Gstreamer packages
# optional: GFX packages
IMAGE_INSTALL_append_rcar-gen3 = " \
    ${WAYLAND_BASE_PKGS} \
    ${GSTREAMER_PKGS} \
    ${@base_conditional("USE_GLES", "1", "${GLES_PKGS}", "", d)} \
    ${@base_conditional("USE_GLES_WAYLAND", "1", "${WAYLAND_GLES_PKGS}", "", d)} \
"

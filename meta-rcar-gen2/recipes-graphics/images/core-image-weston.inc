require include/gles-control.inc

# Wayland basic packages
WAYLAND_BASE_PKGS = " \
    weston-bin \
    alsa-utils \
    alsa-tools \
    libdrm-tests \
    libdrm-kms \
"

# GFX for Wayland packages
WAYLAND_GLES_PKGS = "libgbm-dev"

# GFX driver and library packages
GLES_PKGS = " \
    kernel-module-gles \
    gles-user-module \
    libegl \
    libegl-dev \
"

# Gstreamer Packages
GSTREAMER_PKGS = "packagegroup-gstreamer1.0-plugins"

# Install packages for Wayland Env
# default: Basic packages, Gstreamer packages
# optional: GFX packages
IMAGE_INSTALL_append_rcar-gen2 = " \
    ${WAYLAND_BASE_PKGS} \
    ${GSTREAMER_PKGS} \
    ${@base_conditional("USE_GLES", "1", "${GLES_PKGS}", "", d)} \
    ${@base_conditional("USE_GLES_WAYLAND", "1", "${WAYLAND_GLES_PKGS}", "", d)} \
"

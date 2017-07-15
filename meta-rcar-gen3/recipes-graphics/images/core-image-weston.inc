require include/gles-control.inc
require core-image-renesas-mmp-base.inc

# Gstreamer Packages
GSTREAMER_PKGS = "packagegroup-gstreamer1.0-plugins"

# Install packages for Wayland Env
# default: Basic packages, Gstreamer packages
# optional: GFX packages
IMAGE_INSTALL_append = " \
    ${GSTREAMER_PKGS} \
    packagegroup-wayland-community \
    packagegroup-graphics-renesas-proprietary \
    packagegroup-graphics-renesas-wayland \
"

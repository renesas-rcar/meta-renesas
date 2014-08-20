WAYLAND_ENABLE ?= "0"
require ${@base_conditional("WAYLAND_ENABLE", "1", "recipes-core/images/core-image-minimal.bb", "recipes-sato/images/core-image-sato-sdk.bb", d)}

include bsp-image.inc

IMAGE_INSTALL += " \
        libdrm-kms \
        alsa-utils alsa-tools \
"

# override the IMAGE_FEATURES for remove unused pkgs
IMAGE_FEATURES = "dev-pkgs tools-sdk tools-debug debug-tweaks ssh-server-openssh"

WAYLAND_ENABLE ?= "0"
require ${@base_conditional("WAYLAND_ENABLE", "1", "recipes-core/images/core-image-minimal.bb", "recipes-sato/images/core-image-sato-sdk.bb", d)}

IMAGE_FEATURES += '${@base_conditional("WAYLAND_ENABLE", "1", "dev-pkgs tools-sdk \
    tools-debug eclipse-debug tools-profile debug-tweaks ssh-server-openssh", "", d)}'

IMAGE_INSTALL += '${@base_conditional("WAYLAND_ENABLE", "1", "kernel-dev", "", d)}'

include bsp-image.inc

IMAGE_INSTALL += " \
        libdrm-kms \
        alsa-utils alsa-tools \
"

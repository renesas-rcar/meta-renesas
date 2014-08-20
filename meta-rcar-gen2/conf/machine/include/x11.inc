MULTI_PROVIDER_WHITELIST += "virtual/libgl virtual/egl virtual/libgles1 virtual/libgles2"
PREFERRED_PROVIDER_virtual/libgles2 = " \
        ${@base_contains('MACHINE_FEATURES', 'rgx', 'gles-user-module', \
        base_contains('MACHINE_FEATURES', 'sgx', 'gles-user-module', \
        '', d), d)}"
PREFERRED_PROVIDER_virtual/egl =" \
        ${@base_contains('MACHINE_FEATURES', 'rgx', 'gles-user-module', \
        base_contains('MACHINE_FEATURES', 'sgx', 'gles-user-module', \
        '', d), d)}"
PREFERRED_PROVIDER_virtual/libgl = "mesa-gl"
PREFERRED_PROVIDER_virtual/mesa = "mesa-gl"
DISTRO_FEATURES_remove = "wayland"

WAYLAND_GFX_ENABLE ?= "0"

BBMASK .= "|libegl|libgbm|wayland-kms|wayland-wsegl"
BBMASK .= "|meta-renesas/meta-rcar-gen2/recipes-graphics/cogl"

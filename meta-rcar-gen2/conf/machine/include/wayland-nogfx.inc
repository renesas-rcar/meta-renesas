WAYLAND_GFX_ENABLE ?= "0"

BBMASK .= "|libegl|libgbm|wayland-kms|wayland-wsegl"
BBMASK .= "|meta-renesas/meta-rcar-gen2/recipes-graphics/mesa"
BBMASK .= "|meta-renesas/meta-rcar-gen2/recipes-graphics/cogl"

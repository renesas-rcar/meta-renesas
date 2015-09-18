include core-image-renesas-base.inc

IMAGE_INSTALL_append_rcar-gen3 = \
    '${@base_conditional("USE_GLES_WAYLAND", "1", \
    " libegl libegl-dev libgbm-dev", "", d)}'

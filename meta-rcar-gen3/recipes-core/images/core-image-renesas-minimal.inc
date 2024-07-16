# Add overrides for selection 32 bit or 64 bit target
OVERRIDES .= ":${TARGET_SYS}"

# Support secure environment
IMAGE_INSTALL:append = " \
    optee-client \
"

# Renesas Basic packages
IMAGE_INSTALL:append = " \
    bash \
    v4l-utils \
    i2c-tools \
    coreutils \
"

# Renesas Basic packages for 32bit
BASIC_32BIT_PKGS = " \
    lib32-coreutils \
    lib32-libstdc++ \
    lib32-optee-client \
"

# Installation for 32bit packages
IMAGE_INSTALL:append = " \
    ${@oe.utils.conditional("USE_32BIT_PKGS", "1", "${BASIC_32BIT_PKGS}", "", d)} \
"

#Installation for ethtool
IMAGE_INSTALL:append = " ethtool"

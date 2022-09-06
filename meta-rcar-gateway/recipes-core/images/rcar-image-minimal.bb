SUMMARY = "A small image just capable of allowing a device to boot."

require recipes-core/images/core-image-minimal.bb

COMPATIBLE_MACHINE = "(spider|s4sk-proto)"

# Enable package manager
EXTRA_IMAGE_FEATURES += "package-management"

# Basic packages
IMAGE_INSTALL:append = " \
    bash \
"
IMAGE_INSTALL:append = " \
    optee-client \
"

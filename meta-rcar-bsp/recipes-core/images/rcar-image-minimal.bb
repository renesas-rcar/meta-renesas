SUMMARY = "A small image just capable of allowing a device to boot."

require recipes-core/images/core-image-minimal.bb

COMPATIBLE_MACHINE = "(falcon|condor|eagle|white-hawk)"

# Enable package manager
EXTRA_IMAGE_FEATURES += "package-management"

# Basic packages
IMAGE_INSTALL_append = " \
    bash \
    v4l-utils \
    i2c-tools \
    coreutils \
"

DEPENDS += "perl-native libarchive-zip-perl-native"

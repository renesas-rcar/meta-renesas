SUMMARY = "A image with support for ADAS applications."

require recipes-core/images/rcar-image-minimal.bb

IMAGE_INSTALL:append:rcar = " \
    packagegroup-renesas \
    packagegroup-oss \
    packagegroup-opencv-sdk \
    kernel-image \
    kernel-devicetree \
    kernel-modules \
"
IMAGE_INSTALL:append:rcar-v4x = " \
    nvme-initscripts \
    pcietest \
"


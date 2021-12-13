require rcar-image-minimal.bb

# Packages for Gateway platform
IMAGE_INSTALL_append = " \
    kernel-module-uio-pdrv-genirq \
    kernel-module-cmemdrv \
    kernel-module-cmemdrv-dev \
    udev-rules \
"


FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

do_install_append() {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    mv ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD_append = " dmatest"
KERNEL_MODULE_PROBECONF_append = " dmatest"

# LVDS camera configuration
SRC_URI_append_rcar = " \
    file://lvds_camera.cfg \
"

PACKAGES += "${PN}-uapi"

# Install RCAR Gen3 specific UAPI headers
do_install_append_rcar-v4x() {
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-imr.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/
}

FILES_${PN}-uapi = "/usr/include"

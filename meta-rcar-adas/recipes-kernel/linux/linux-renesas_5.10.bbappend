FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

PACKAGES += "${PN}-uapi"

do_install_append_rcar() {
    # Install R-Car specific UAPI headers
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/

    # Install dmatest module
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    mv ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

FILES_${PN}-uapi = "/usr/include"

# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD_append = " dmatest"
KERNEL_MODULE_PROBECONF_append = " dmatest"

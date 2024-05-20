FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

PACKAGES += "${PN}-uapi"

do_install:append:rcar() {
    # Install R-Car specific UAPI headers
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/

    # Install dmatest module
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    mv ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

FILES:${PN}-uapi = "/usr/include"

# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD:append = " dmatest"
KERNEL_MODULE_PROBECONF:append = " dmatest"

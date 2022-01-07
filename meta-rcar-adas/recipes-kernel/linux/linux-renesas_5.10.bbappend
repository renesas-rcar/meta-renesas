FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

# LVDS camera configuration
SRC_URI_append_rcar = " \
    file://lvds_camera.cfg \
"

PACKAGES += "${PN}-uapi"

# Install RCAR Gen3 specific UAPI headers
do_install_append_rcar-v4x() {
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/
}

FILES_${PN}-uapi = "/usr/include"

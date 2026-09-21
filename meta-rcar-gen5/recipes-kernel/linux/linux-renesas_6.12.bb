DESCRIPTION = "Linux kernel for the R-Car X5x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "x5h_vpf|x5h_evb|ironhide|perceptor"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
    file://mvq32xx.ko \
    file://ar0820.ko \
    file://imx728.ko \
    file://max96712.ko \
    file://Enable-DRM_TTM_HELPER-by-default.patch \
"

# Add MP-PHY firmware conditionally for R-Car X5H board
SRC_URI:append:rcar-gen5-evb = " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rcar-firmware', \
        'file://rcar_gen5_mp_phy.bin \
        file://rcar_gen5_pcie6_iccm.bin \
        file://rcar_gen5_pcie6_dccm.bin \
        file://rcar-fw.cfg', '', d)} \
        file://0001-arm64-dts-renesas-r8a78000-Add-multifwk-dts-1.patch \
"

LINUX_VERSION ?= "6.12.80"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "renesas_bsp_defconfig"

PACKAGES += "${PN}-uapi"

do_install:append:rcar-gen5() {
    # Install R-Car specific UAPI headers
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/

    # Install dmatest module
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    mv ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    install -d ${D}${sysconfdir}/modprobe.d/
    echo "blacklist max96712" >> ${D}${sysconfdir}/modprobe.d/blacklist.conf
    echo "blacklist pci_endpoint_test" >> ${D}${sysconfdir}/modprobe.d/blacklist.conf
}

do_install:append:rcar-gen5-evb() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${UNPACKDIR}/mvq32xx.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${UNPACKDIR}/ar0820.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${UNPACKDIR}/imx728.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${UNPACKDIR}/max96712.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

do_install:append:rcar-gen5-vpf() {
    install -d ${D}${sysconfdir}/modprobe.d/
    echo "blacklist ucie-ldr-map" >> ${D}${sysconfdir}/modprobe.d/blacklist.conf
    echo "blacklist ucie-dummy-rcar-host" >> ${D}${sysconfdir}/modprobe.d/blacklist.conf
    echo "blacklist ucie-dummy-rcar" >> ${D}${sysconfdir}/modprobe.d/blacklist.conf
}

# Install R-Car Gen5 firmware to staging directory if enabled for R-Car X5H board
do_install_rcar_gen5_firmware () {
    case "${MACHINE}" in
        x5h_evb|ironhide|perceptor)
        install -d ${STAGING_KERNEL_DIR}/firmware
        install -m 644 ${UNPACKDIR}/rcar_gen5_mp_phy.bin ${STAGING_KERNEL_DIR}/firmware/
        install -m 644 ${UNPACKDIR}/rcar_gen5_pcie6_iccm.bin ${STAGING_KERNEL_DIR}/firmware/
        install -m 644 ${UNPACKDIR}/rcar_gen5_pcie6_dccm.bin ${STAGING_KERNEL_DIR}/firmware/
        ;;
    esac
}

addtask do_install_rcar_gen5_firmware after do_configure before do_compile

# Deploy vmlinux to deploy directory
do_deploy:append:rcar-gen5() {
    install -m 0644 ${KERNEL_OUTPUT_DIR}/vmlinux $deployDir/
}

FILES:${PN}-uapi = "/usr/include"

FILES:${KERNEL_PACKAGE_NAME}-modules += " \
    ${sysconfdir}/modprobe.d/blacklist.conf \
"

# uio_pdrv_genirq and dmatest configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq dmatest"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq dmatest i3c-rcar-master at24 rcar_canfd"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'
module_conf_i3c-rcar-master = "blacklist i3c-rcar-master"
module_conf_at24 = "blacklist at24"
module_conf_rcar_canfd = "blacklist rcar_canfd"
INSANE_SKIP += "buildpaths"
INSANE_SKIP:${PN} += "installed-vs-shipped"

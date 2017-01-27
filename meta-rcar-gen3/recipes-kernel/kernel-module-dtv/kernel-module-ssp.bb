DESCRIPTION = "DTV Driver part of ssp for Linux for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc
require include/dtv-dvd-control.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://ssp_drv/include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://ssp_drv/include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

inherit module distro_features_check

DEPENDS = "linux-renesas"
PN = "kernel-module-ssp"
PR = "r0"

REQUIRED_DISTRO_FEATURES = "dtv"

SRC_URI = "file://ssp_drv.tar.gz"

S = "${WORKDIR}"

EXTRA_OEMAKE_r8a7795 += "DTV_MAKE_HW_SWITCH=HW_SUPPORT_H3"
EXTRA_OEMAKE_r8a7796 += "DTV_MAKE_HW_SWITCH=HW_SUPPORT_M3"

do_compile() {
    cd ${S}/ssp_drv/drv
    oe_runmake
}

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${KERNELSRC}/include

    # Install kernel module
    install -m 644 ${S}/ssp_drv/drv/ssp_dev.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR).
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/ssp_drv/include/*.h ${KERNELSRC}/include
    install -m 644 ${S}/ssp_drv/drv/Module.symvers ${KERNELSRC}/include/ssp_drv.symvers
}

PACKAGES = "\
    ${PN} \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/ssp_dev.ko \
"

RPROVIDES_${PN} += "kernel-module-ssp"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

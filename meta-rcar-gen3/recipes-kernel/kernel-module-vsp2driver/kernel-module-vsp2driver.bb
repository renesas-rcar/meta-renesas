DESCRIPTION = "VSP2Driver for the R-Car Gen3"

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

require include/rcar-gen3-modules-common.inc

inherit module

DEPENDS = "linux-renesas kernel-module-vspm"
PN = "kernel-module-vsp2driver"
PR = "r0"

VSP2DRIVER_URL = " \
    git://github.com/renesas-rcar/vsp2driver.git"
BRANCH = "rcar-gen3"
SRCREV = "0ecf35aa05c103ba4d3ab83e3320a22fccd912c3"

SRC_URI = "${VSP2DRIVER_URL};branch=${BRANCH}"

SRC_URI_append = " file://0001-Handle-data_offset-setting-from-V4L2-API.patch"

S = "${WORKDIR}/git"

# Build VSP2 driver kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    cd ${S}/vsp2driver
    make all
}

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${KERNELSRC}/include

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/vsp2driver/Module.symvers ${KERNELSRC}/include/vsp2.symvers

    # Copy kernel module
    install -m 644 ${S}/vsp2driver/vsp2.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/vsp2driver/linux/vsp2.h ${KERNELSRC}/include/
}

PACKAGES = "\
    ${PN} \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vsp2.ko \
    ${sysconfdir}/modules-load.d \
"

RPROVIDES_${PN} += "kernel-module-vsp2driver kernel-module-vsp2"

# Autoload VSP2Driver
KERNEL_MODULE_AUTOLOAD_append = " vsp2"

DESCRIPTION = "VSP2Driver for the R-Car Gen3"

LICENSE = "GPL-2.0-only & MIT"
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
SRCREV = "b3a116d8ce68371cac21011ca3b3190ae3576987"

SRC_URI = "${VSP2DRIVER_URL};branch=${BRANCH};protocol=https"

S = "${WORKDIR}/git"

# Build VSP2 driver kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    cd ${S}/vsp2driver
    make all
}

do_install () {
    # Create destination directories
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/vsp2driver/Module.symvers ${KERNELSRC}/include/vsp2.symvers

    # Copy kernel module
    install -m 644 ${S}/vsp2driver/vsp2.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/vsp2driver/linux/vsp2.h ${KERNELSRC}/include/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/vsp2.ko \
    ${sysconfdir}/modules-load.d \
"

FILES:${PN}-dbg = "" 
ALLOW_EMPTY:${PN}-dbg = "1" 

RPROVIDES:${PN} += "kernel-module-vsp2driver kernel-module-vsp2"

# Autoload VSP2Driver
KERNEL_MODULE_AUTOLOAD:append = " vsp2"

DESCRIPTION = "VSP Driver (vspm based) for the R-Car Gen2"

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"
require include/rcar-gen2-modules-common.inc

inherit module

DEPENDS = "linux-renesas kernel-module-vspm"
RDEPENDS_${PN} = "kernel-module-vspm"

PN = "kernel-module-vsp2"
PR = "r0"

SRCREV = "8c172771d0fdec0ab7afb69a05f611370bd96489"
SRC_URI = " \
    git://github.com/renesas-devel/vsp2driver.git;protocol=git;branch=RCAR-GEN2/1.0.0 \
    file://0001-port-vsp2-to-kernel-4.4.patch \
    file://0002-fix-vsp2_exit-call.patch \
    file://0003-vsp2-Update-from-kernel-4.4-to-kernel-4.6.patch \
"

S = "${WORKDIR}/git"

do_compile() {
    export VSP2_VSPMDIR=${KERNELSRC}/include
    export VSP2_VSPMSYMVERS=vspm.symvers
    cd ${S}/drv
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    install -d  ${D}/lib/modules/${KERNEL_VERSION}/extra/ 
    install -d  ${KERNELSRC}/include

    # Copy kernel module
    install -m 644 ${S}/drv/vsp2.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/drv/Module.symvers ${KERNELSRC}/include/vsp2.symvers
}

PACKAGES = "\
    ${PN} \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vsp2.ko \
"

RPROVIDES_${PN} += "kernel-module-vsp2"

KERNEL_MODULE_AUTOLOAD = "vsp2"


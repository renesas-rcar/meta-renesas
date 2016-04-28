DESCRIPTION = "DTV Driver part of tddmac for Linux for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc
require include/dtv-dvd-control.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://tddmac_drv/include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://tddmac_drv/include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-tddmac"
PR = "r0"

REQUIRED_DISTRO_FEATURES = "dtv"

SRC_URI = "file://tddmac_drv.tar.gz"

S = "${WORKDIR}"

# do_configure() nothing
do_configure[noexec] = "1"

do_compile() {
    cd ${S}/tddmac_drv/drv
    oe_runmake
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/src/kernel/include
    install -d ${KERNELSRC}/include

    # Copy kernel module
    install -m 644 ${S}/tddmac_drv/drv/tddmac.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Copy shared header files
    install -m 644 ${S}/tddmac_drv/include/*.h ${KERNELSRC}/include
    install -m 644 ${S}/tddmac_drv/drv/Module.symvers ${KERNELSRC}/include/tddmac_drv.symvers
    # Export SDK
    install -m 644 ${S}/tddmac_drv/include/*.h ${D}/usr/src/kernel/include
    install -m 644 ${S}/tddmac_drv/drv/Module.symvers ${D}/usr/src/kernel/include/tddmac_drv.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/tddmac.ko \
"
FILES_${PN}-dev = " \
    /usr/src/kernel/include/*.h \
    /usr/src/kernel/include/tddmac_drv.symvers \
"

RPROVIDES_${PN} += "kernel-module-tddmac"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

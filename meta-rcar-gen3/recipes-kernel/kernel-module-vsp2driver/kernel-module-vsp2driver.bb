DESCRIPTION = "VSP2Driver for the R-Car Gen3"

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

require ../../include/rcar-gen3-modules-common.inc

inherit module

DEPENDS = "linux-renesas kernel-module-vspm"
PN = "kernel-module-vsp2driver"
PR = "r0"

VSP2DRIVER_URL = " \
    git://github.com/renesas-rcar/vsp2driver.git"
BRANCH = "rcar-gen3"
SRCREV = "5006e38639c0f047203e6d2d256df94d4b90b617"

SRC_URI = "${VSP2DRIVER_URL};protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile() {
    cd ${S}/vsp2driver
    make all
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include

    # Copy shared library for reference from other modules
    install -m 644 ${S}/vsp2driver/Module.symvers ${KERNELSRC}/include/vsp2.symvers
    install -m 644 ${S}/vsp2driver/Module.symvers ${D}/usr/src/kernel/include/vsp2.symvers

    # Copy kernel module
    install -m 644 ${S}/vsp2driver/vsp2.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # copy shared header files
    install -m 644 ${S}/vsp2driver/linux/vsp2.h ${D}/usr/src/kernel/include
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vsp2.ko \
"
FILES_${PN}-dev = " \
    /usr/src/kernel/include/vsp2.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "kernel-module-vsp2driver kernel-module-vsp2"

do_configure[noexec] = "1"

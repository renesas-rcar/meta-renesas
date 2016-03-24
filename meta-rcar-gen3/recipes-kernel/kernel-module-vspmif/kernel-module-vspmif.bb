DESCRIPTION = "VSP Manager Interface driver for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

inherit module

DEPENDS = "linux-renesas kernel-module-vspm"
PN = "kernel-module-vspmif"
PR = "r0"

VSPMIF_DRV_URL = " \
    git://github.com/renesas-rcar/vspmif_drv.git"
BRANCH = "rcar_gen3"
SRCREV = "96e1e575d226467e7c69f1035361fabaff59a8db"

SRC_URI = "${VSPMIF_DRV_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
VSPMIF_DRV_DIR = "vspm_if-module/files/vspm_if"

do_compile() {
    cd ${S}/${VSPMIF_DRV_DIR}/drv
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/include/vspm_if.h ${BUILDDIR}/include
    make all
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/src/kernel/include
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/local/include

    # Copy shared library for reference from other modules
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/vspm_if.symvers
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/drv/Module.symvers ${D}/usr/src/kernel/include/vspm_if.symvers

    # Copy kernel module
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/drv/vspm_if.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # copy shared header files
    install -m 644 ${KERNELSRC}/include/vspm_if.h ${D}/usr/src/kernel/include
    install -m 644 ${KERNELSRC}/include/vspm_if.h ${D}/usr/local/include
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vspm_if.ko \
"
FILES_${PN}-dev = " \
    /usr/src/kernel/include/vspm_if.symvers \
    /usr/src/kernel/include/*.h \
    /usr/local/include/*.h \
"

RPROVIDES_${PN} += "kernel-module-vspmif kernel-module-vspm-if"

do_configure[noexec] = "1"

DESCRIPTION = "VSP Manager for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-vspm"
PR = "r0"

VSPM_DRV_URL = "git://github.com/renesas-rcar/vspm_drv.git"
BRANCH = "rcar_gen3"
SRCREV = "95298abddca04fe80f7e5104a3b03b2f06baf6f6"

SRC_URI = "${VSPM_DRV_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
VSPM_DRV_DIR = "vspm-module/files/vspm"

do_compile() {
    cd ${S}/${VSPM_DRV_DIR}/drv
    make all
}

do_install () {
    # Create destination folders
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/src/kernel/include

    # Copy shared library for reference from other modules
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/vspm.symvers
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/Module.symvers ${D}/usr/src/kernel/include/vspm.symvers

    # Copy kernel module
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/vspm.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # copy shared header files
    install -m 644 ${KERNELSRC}/include/vspm_public.h ${D}/usr/src/kernel/include
    install -m 644 ${KERNELSRC}/include/vsp_drv.h ${D}/usr/src/kernel/include
    install -m 644 ${KERNELSRC}/include/fdp_drv.h ${D}/usr/src/kernel/include
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vspm.ko \
"
FILES_${PN}-dev = " \
    /usr/src/kernel/include/vspm.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "kernel-module-vspm"

do_configure[noexec] = "1"

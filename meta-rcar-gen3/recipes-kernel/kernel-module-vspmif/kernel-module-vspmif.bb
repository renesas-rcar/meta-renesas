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
SRCREV = "6172cc7273aae0345db894faa5ab59777549c247"

SRC_URI = "${VSPMIF_DRV_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
VSPMIF_DRV_DIR = "vspm_if-module/files/vspm_if"

includedir = "${RENESAS_DATADIR}/include"

# Build VSP Manager Interface kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    cd ${S}/${VSPMIF_DRV_DIR}/drv
    make all
}

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}
    install -d ${KERNELSRC}/include

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/vspm_if.symvers

    # Install kernel module
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/drv/vspm_if.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/include/vspm_if.h ${KERNELSRC}/include/

    # Install shared header file
    install -m 644 ${S}/${VSPMIF_DRV_DIR}/include/vspm_if.h ${D}/${includedir}/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vspm_if.ko \
"

RPROVIDES_${PN} += "kernel-module-vspmif kernel-module-vspm-if"

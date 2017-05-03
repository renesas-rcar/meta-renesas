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
SRCREV = "2bdd8fa3b21a44f0677657d2bf0d7c7f3656abad"

SRC_URI = "${VSPM_DRV_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
VSPM_DRV_DIR = "vspm-module/files/vspm"
includedir = "${RENESAS_DATADIR}/include"

do_compile() {
    cd ${S}/${VSPM_DRV_DIR}/drv
    make all
}

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${KERNELSRC}/include
    install -d ${D}/${includedir}

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/vspm.symvers

    # Install kernel module
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/vspm.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/vspm_public.h ${KERNELSRC}/include/
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/vspm_cmn.h ${KERNELSRC}/include/
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/vsp_drv.h ${KERNELSRC}/include/
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/fdp_drv.h ${KERNELSRC}/include/

    # Install shared header files
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/vspm_cmn.h ${D}/${includedir}/
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/vsp_drv.h ${D}/${includedir}/
    install -m 644 ${S}/${VSPM_DRV_DIR}/include/fdp_drv.h ${D}/${includedir}/
}

do_populate_sysroot[sstate-inputdirs] += "${S}/${VSPM_DRV_DIR}/include/"
do_populate_sysroot[sstate-outputdirs] += "${KERNELSRC}/include/"
do_populate_sysroot_setscene[prefuncs] = "vspm_sstate_check_func"
SSTATE_DUPWHITELIST = "${KERNELSRC}/include"

vspm_sstate_check_func() {
    # An error is returned when unpack of kernel source has not been completed yet.
    # By returning error, rebuild task runs by force (Invalidating sstate).
    # This module installs shared header files in ${KERNELSRC}/include by
    # sstate cache.
    # Those files will be deleted by unpack task of kernel.
    if [ ! -d "${KERNELSRC}/include" ]; then
        exit 1
    fi
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vspm.ko \
"

RPROVIDES_${PN} += "kernel-module-vspm"

# Autoload VSPM
KERNEL_MODULE_AUTOLOAD = "vspm"

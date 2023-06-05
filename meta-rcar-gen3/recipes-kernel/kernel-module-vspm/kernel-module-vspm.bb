DESCRIPTION = "VSP Manager for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc

LICENSE = "GPL-2.0-only & MIT"
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
SRCREV = "07787fc1168e7fe37c305aca151a6f756f35874f"

SRC_URI = "${VSPM_DRV_URL};branch=${BRANCH};protocol=https"

S = "${WORKDIR}/git"
VSPM_DRV_DIR = "vspm-module/files/vspm"
includedir = "${RENESAS_DATADIR}/include"

# Build VSP Manager kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    cd ${S}/${VSPM_DRV_DIR}/drv
    make all
}

do_install () {
    # Create destination directories
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/Module.symvers ${KERNELSRC}/include/vspm.symvers

    # Install kernel module
    install -m 644 ${S}/${VSPM_DRV_DIR}/drv/vspm.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

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
SSTATE_ALLOW_OVERLAP_FILES = "${KERNELSRC}/include"

vspm_sstate_check_func() {
    # An error is returned when unpack of kernel source has not been completed yet.
    # By returning error, rebuild task runs by force (Invalidating sstate).
    # This module installs shared header files in ${KERNELSRC}/include by
    # sstate cache.
    # Those files will be deleted by unpack task of kernel.
    if [ ${WITHIN_EXT_SDK} -eq 1 ]; then
        :
    else
        if [ ! -d "${KERNELSRC}/include" ]; then
            exit 1
        fi
    fi
}

# Should also clean deploy/licenses directory
# for module when do_clean.
do_clean[cleandirs] += "${LICENSE_DIRECTORY}/${PN}"

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/vspm.ko \
"

FILES:${PN}-dbg = "" 
ALLOW_EMPTY:${PN}-dbg = "1" 

RPROVIDES:${PN} += "kernel-module-vspm"

# Autoload VSPM
KERNEL_MODULE_AUTOLOAD:append = " vspm"

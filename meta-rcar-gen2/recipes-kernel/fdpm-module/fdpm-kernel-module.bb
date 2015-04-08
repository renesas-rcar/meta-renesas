require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://drv/GPL-COPYING;md5=ffa10f40b98be2c2bc9608f56827ed23 \
    file://drv/MIT-COPYING;md5=5526ef6e21dc96a1dd89fac4bde9f995"

DEPENDS = "linux-renesas mmngr-kernel-module"
PN = "fdpm-kernel-module"
PR = "r0"
SRC_URI = "file://fdpm-kernel.tar.bz2"
S = "${WORKDIR}/fdpm"

FDPM_CFG_r8a7790 = "H2CONFIG"
FDPM_CFG_r8a7791 = "M2CONFIG"
FDPM_CFG_r8a7793 = "M2CONFIG"
FDPM_CFG_r8a7794 = "E2CONFIG"

KERNEL_HEADER_PATH = "${KERNELSRC}/include/linux"
FDPM_INSTALL_HEADERS="fdpm_drv.h fdpm_public.h fdpm_api.h"

do_compile() {
    # Build kernel module
    export FDPM_CONFIG=${FDPM_CFG}
    export FDPM_MMNGRDIR=${KERNELSRC}/include
    export FDPM_MMNGRSYMVERS=mmngr.symvers
    cd ${S}/drv
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra ${D}/usr/src/kernel/include

    # Copy driver and header files
    cp -f ${S}/drv/fdpm.ko ${D}/lib/modules/${KERNEL_VERSION}/extra
    cp ${S}/drv/Module.symvers ${KERNELSRC}/include/fdpm.symvers

    for f in ${FDPM_INSTALL_HEADERS} ; do
        cp -f ${KERNEL_HEADER_PATH}/${f} ${KERNELSRC}/include
    done

    # Copy header files to destination
    for f in ${FDPM_INSTALL_HEADERS} ; do
        cp -f ${KERNEL_HEADER_PATH}/${f} ${D}/usr/src/kernel/include
    done
    cp -f ${S}/drv/Module.symvers ${D}/usr/src/kernel/include/fdpm.symvers
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    # Check if kernel source exists before doing cleansstate
    if [ -d ${KERNELSRC} ] ; then
        for f in fdpm_drv.h fdpm.symvers ${FDPM_INSTALL_HEADERS} ; do
            find ${KERNELSRC} -name ${f} -delete
        done
    fi
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/fdpm.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include/*.h \
    /usr/src/kernel/include/fdpm.symvers \
"

RPROVIDES_${PN} += "fdpm-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

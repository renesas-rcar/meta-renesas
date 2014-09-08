require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://vspm/drv/GPL-COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
    file://vspm/drv/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"
DEPENDS = "linux-renesas"
PN = "vspm-kernel-module"
PR = "r0"
SRC_URI = "file://vspm-kernel.tar.bz2"
S = "${WORKDIR}"

FDPM_CFG_r8a7790 = "H2CONFIG"
FDPM_CFG_r8a7791 = "M2CONFIG"
FDPM_CFG_r8a7793 = "M2CONFIG"
FDPM_CFG_r8a7794 = "E2CONFIG"

do_configure[noexec] = "1"
do_compile() {
    export VSPM_CONFIG=${VSPM_CFG}
    cd ${S}/vspm/drv
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include

    # Copy kernel module
    cp -f ${S}/vspm/drv/vspm.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Copy shared header files
    cp -f ${KERNELSRC}/include/vspm_public.h ${D}/usr/src/kernel/include
    cp -f ${KERNELSRC}/include/vsp_drv.h ${D}/usr/src/kernel/include
    cp -f ${KERNELSRC}/include/tddmac_drv.h ${D}/usr/src/kernel/include
    cp -f ${KERNELSRC}/include/vspm_if.h ${D}/usr/src/kernel/include
    cp -f ${S}/vspm/drv/Module.symvers ${D}/usr/src/kernel/include/vspm.symvers
    cp -f ${S}/vspm/drv/Module.symvers ${KERNELSRC}/include/vspm.symvers

    # Copy for vspm-user-module
    cp -f ${KERNELSRC}/include/vspm_if.h ${BUILDDIR}/include
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${KERNELSRC}/include/vspm_public.h
    rm -f ${KERNELSRC}/include/vsp_drv.h
    rm -f ${KERNELSRC}/include/tddmac_drv.h
    rm -f ${KERNELSRC}/include/vspm_if.h
    rm -f ${BUILDDIR}/include/vspm_if.h
    rm -f ${KERNELSRC}/include/vspm.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vspm.ko \
"

FILES_${PN}-dev = " \
  /usr/src/kernel/include \
  /usr/src/kernel/include/*.h \
  /usr/src/kernel/include/vspm.symvers \
"

RPROVIDES_${PN} += "vspm-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

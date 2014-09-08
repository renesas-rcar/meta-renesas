require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://ssp/drv/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://ssp/drv/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"
DEPENDS = "linux-renesas"
PN = "ssp-kernel-module"
PR = "r0"
SRC_URI = "file://ssp-kernel.tar.bz2"

S = "${WORKDIR}"

do_compile() {
    cd ${S}/ssp/drv
    make clean ARCH=arm
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include/
    mkdir -p ${KERNELSRC}/include

    # Copy kernel module
    cp ${S}/ssp/drv/ssp_dev.ko ${D}/lib/modules/${KERNEL_VERSION}/extra

    # Copy shared header files
    cp -f ${S}/ssp/include/ssp_dev_private.h  ${KERNELSRC}/include
    cp -f ${S}/ssp/include/ssp_dev_public.h  ${KERNELSRC}/include
    cp -f ${S}/ssp/drv/Module.symvers ${KERNELSRC}/include/ssp-drv.symvers

    # Export for SDK
    cp -f ${S}/ssp/include/ssp_dev_private.h  ${D}/usr/src/kernel/include
    cp -f ${S}/ssp/include/ssp_dev_public.h  ${D}/usr/src/kernel/include
    cp -f ${S}/ssp/drv/Module.symvers ${D}/usr/src/kernel/include/ssp-drv.symvers
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${KERNELSRC}/include/ssp_dev_private.h ${KERNELSRC}/include/ssp_dev_public.h
    rm -f ${KERNELSRC}/include/ssp-drv.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/ssp_dev.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include \
    /usr/src/kernel/include/*.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "ssp-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://src/include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://src/include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"
DEPENDS = "linux-renesas"
PN = "scu-kernel-module"
PR = "r0"
SRC_URI = "file://scu-kernel.tar.bz2"
S = "${WORKDIR}"
do_compile() {
    cd ${S}/src/drv
    make clean ARCH=arm
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include/
    mkdir -p ${KERNELSRC}/include

    # Copy kernel module
    cp ${S}/src/drv/scu_src_drv.ko ${D}/lib/modules/${KERNEL_VERSION}/extra

    # Copy shared header files
    cp -f ${S}/src/include/scu_src_drv.h  ${KERNELSRC}/include
    cp -f ${S}/src/drv/Module.symvers ${KERNELSRC}/include/scu-drv.symvers
    # Export for SDK
    cp -f ${S}/src/include/scu_src_drv.h  ${D}/usr/src/kernel/include
    cp -f ${S}/src/drv/Module.symvers ${D}/usr/src/kernel/include/scu-drv.symvers
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${KERNELSRC}/include/scu_src_drv.h ${KERNELSRC}/include/scu-drv.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/scu_src_drv.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include \
    /usr/src/kernel/include/*.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "scu-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

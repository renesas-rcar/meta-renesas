require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://drv/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://drv/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378"
DEPENDS = "linux-renesas"
PN = "s3ctl-kernel-module"
PR = "r0"
SRC_URI = "file://s3ctl-kernel.tar.bz2"

S = "${WORKDIR}/s3ctl"

do_compile() {
    # Build kernel module
    cd ${S}/drv
    make all ARCH=arm
}

do_install() {
    # Create shared folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include

    # Copy kernel module
    cp -f ${S}/drv/s3ctl.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Copy shared header files
    cp -f ${S}/drv/Module.symvers ${KERNELSRC}/include/s3ctl.symvers
    cp -f ${KERNELSRC}/include/s3ctl_private.h ${D}/usr/src/kernel/include
    cp -f ${S}/drv/Module.symvers ${D}/usr/src/kernel/include/s3ctl.symvers
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -rf ${KERNELSRC}/include/s3ctl_private.h
    rm -rf ${KERNELSRC}/include/s3ctl.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/s3ctl.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include \
    /usr/src/kernel/include/*.h \
    /usr/src/kernel/include/s3ctl.symvers \
"

RPROVIDES_${PN} += "s3ctl-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

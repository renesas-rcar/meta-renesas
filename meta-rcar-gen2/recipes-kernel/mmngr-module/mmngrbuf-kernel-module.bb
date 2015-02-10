require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
DEPENDS = "linux-renesas"
PN = "mmngrbuf-kernel-module"
PR = "r0"
SRC_URI = "file://mmngrbuf.tar.bz2"
LIC_FILES_CHKSUM = "file://drv/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://drv/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
    file://include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 "

S = "${WORKDIR}/mmngrbuf"

do_compile() {
    cd ${S}/drv
    make all ARCH=arm
}

do_install () {
    # Create destination folders
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include

    # Copy shared library for reference from other modules
    cp -f ${S}/drv/Module.symvers ${KERNELSRC}/include/mmngrbuf.symvers
    cp -f ${S}/drv/Module.symvers ${D}/usr/src/kernel/include/mmngrbuf.symvers

    # Copy kernel module
    cp -f ${S}/drv/mmngrbuf.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${KERNELSRC}/include/mmngr_buf_private.h
    rm -f ${KERNELSRC}/include/mmngrbuf.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/mmngrbuf.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include/mmngrbuf.symvers \
    /usr/src/kernel/include/*.h \
"

RPROVIDES_${PN} += "mmngrbuf-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

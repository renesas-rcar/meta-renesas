require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://uvcs/include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://uvcs/include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"
DEPENDS = "linux-renesas"
PN = "uvcs-kernel-module"
PR = "r0"
SRC_URI = "file://uvcs-kernel.tar.bz2"
S = "${WORKDIR}"

export UVCS_DRV_SRC_DIR = "${S}/uvcs/source/uvcs_lkm"
export UVCS_CMN_SRC_DIR = "${S}/uvcs/source/uvcs_cmn"
export UVCS_CMN_INC_DIR = "${S}/uvcs/include"
export DRV_CORE_SRC_DIR = "${S}/uvcs/source/driver_core"

do_compile() {
    cd ${S}/uvcs/source/makefile/linaro_4_7_3/
    make clean ARCH=arm
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include

    # Copy kernel module
    cp -f ${S}/uvcs/source/makefile/linaro_4_7_3/uvcs_cmn.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/uvcs_cmn.ko

    # Copy shared header files
    cp -f ${S}/uvcs/include/uvcs_cmn.h  ${D}/usr/src/kernel/include
    cp -f ${S}/uvcs/include/uvcs_types.h  ${D}/usr/src/kernel/include
    cp -f ${S}/uvcs/source/makefile/linaro_4_7_3/Module.symvers  ${D}/usr/src/kernel/include/uvcs.symvers

    cp -f ${S}/uvcs/include/uvcs_cmn.h  ${KERNELSRC}/include/
    cp -f ${S}/uvcs/include/uvcs_types.h  ${KERNELSRC}/include/
    cp -f ${S}/uvcs/source/makefile/linaro_4_7_3/Module.symvers  ${KERNELSRC}/include/uvcs.symvers
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
	rm -f ${KERNELSRC}/include/uvcs_cmn.h ${KERNELSRC}/include/uvcs_types.h
	rm -f ${KERNELSRC}/include/uvcs.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/uvcs_cmn.ko \
"
FILES_${PN}-dev = " \
    /usr/src/kernel/include \
    /usr/src/kernel/include/*.h \
    /usr/src/kernel/include/uvcs.symvers \
"

RPROVIDES_${PN} += "uvcs-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

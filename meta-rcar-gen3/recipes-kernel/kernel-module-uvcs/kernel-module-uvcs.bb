DESCRIPTION = "Kernel module of UVCS"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://include/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://src/lkm/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://src/core/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://src/cmn/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://include/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
    file://src/lkm/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
    file://src/core/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
    file://src/cmn/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"
require include/rcar-gen3-omx-options.inc
inherit module
PN = "kernel-module-uvcs"
PR = "r0"

UVCS_SRC = "${@base_conditional('USE_VIDEO_OMX', '1', 'file://RCG3VUDRL4001ZDO.tar.bz2', '', d)}"
SRC_URI = "${UVCS_SRC}"
S = "${WORKDIR}/RCG3VUDRL4001ZDO"

KBUILD_DIR = "${S}/src/makefile"
KBUILD_OUTDIR = "${KBUILD_DIR}"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OEMAKE += "CROSS_COMPILE=${CROSS_COMPILE}"

module_do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    export UVCS_SRC="${S}/src"
    export UVCS_INC="${S}"
    export VCP4_SRC="${S}/src"
    cd ${KBUILD_DIR}
    oe_runmake
}

module_do_install() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    cd ${KBUILD_DIR}
    oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" \
        CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
        O=${STAGING_KERNEL_BUILDDIR} \
        -C ${STAGING_KERNEL_BUILDDIR} M=${KBUILD_DIR} \
        modules_install
}

# Ship the module symbol file to kerenel build dir
SYSROOT_PREPROCESS_FUNCS = "module_sysroot_symbol"

module_sysroot_symbol() {
    install -m 644 ${KBUILD_OUTDIR}/Module.symvers \
    ${STAGING_KERNEL_BUILDDIR}/UVCS.symvers
}

# Clean up the module symbol file
CLEANFUNCS = "module_clean_symbol"

module_clean_symbol() {
    rm -f ${STAGING_KERNEL_BUILDDIR}/UVCS.symvers
}

RPROVIDES_${PN} += "kernel-module-uvcs"

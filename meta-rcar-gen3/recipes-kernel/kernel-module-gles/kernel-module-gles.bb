DESCRIPTION = "Kernel module of PowerVR GPU"
LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=8c2810fa6bfdc5ae5c15a0c1ade34054 \
"
inherit module
PN = "kernel-module-gles"
PR = "r0"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796|r8a77965|r8a77990)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI:r8a7795 = 'file://GSX_KM_H3.tar.bz2'
SRC_URI:r8a7796 = 'file://GSX_KM_M3.tar.bz2'
SRC_URI:r8a77965 = 'file://GSX_KM_M3N.tar.bz2'
SRC_URI:r8a77990 = 'file://GSX_KM_E3.tar.bz2'
SRC_URI:append = " file://blacklist.conf"
SRC_URI:append = " file://0001-fixing-implicit-conversion-in-GCC-11.2.patch"
S = "${WORKDIR}/rogue_km"

KBUILD_DIR:r8a7795 = "${S}/build/linux/r8a7795_linux"
KBUILD_DIR:r8a7796 = "${S}/build/linux/r8a7796_linux"
KBUILD_DIR:r8a77965 = "${S}/build/linux/r8a77965_linux"
KBUILD_DIR:r8a77990 = "${S}/build/linux/r8a7799_linux"
KBUILD_OUTDIR:r8a7795 = "binary_r8a7795_linux_nullws_drm_release/target_aarch64/kbuild"
KBUILD_OUTDIR:r8a7796 = "binary_r8a7796_linux_nullws_drm_release/target_aarch64/kbuild"
KBUILD_OUTDIR:r8a77965 = "binary_r8a77965_linux_nullws_drm_release/target_aarch64/kbuild"
KBUILD_OUTDIR:r8a77990 = "binary_r8a7799_linux_nullws_drm_release/target_aarch64/kbuild"

B = "${KBUILD_DIR}"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OEMAKE += "CROSS_COMPILE=${CROSS_COMPILE}"

# Build GFX kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

module_do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    cd ${KBUILD_DIR}
    oe_runmake
}

module_do_install() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
    cd ${KBUILD_DIR}
    oe_runmake DISCIMAGE="${D}" install
    rm ${D}/etc/powervr_ddk_install_km.log
    # Install blacklist config file
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 644 ${WORKDIR}/blacklist.conf ${D}${sysconfdir}/modprobe.d/blacklist.conf
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        mv ${D}/lib/modules/${KERNEL_VERSION}/* ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/
        rm -rf ${D}/lib
    fi
}

# Ship the module symbol file to kernel build dir
SYSROOT_PREPROCESS_FUNCS = "module_sysroot_symbol"

module_sysroot_symbol() {
    install -m 644 ${S}/${KBUILD_OUTDIR}/Module.symvers ${STAGING_KERNEL_BUILDDIR}/GLES.symvers
}

# Clean up the module symbol file
CLEANFUNCS = "module_clean_symbol"

module_clean_symbol() {
    rm -f ${STAGING_KERNEL_BUILDDIR}/GLES.symvers
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/pvrsrvkm.ko \
    ${sysconfdir}/modules-load.d \
    ${sysconfdir}/modprobe.d/blacklist.conf \
"

RPROVIDES:${PN} += "kernel-module-pvrsrvkm kernel-module-dc-linuxfb"

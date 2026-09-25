DESCRIPTION = "Kernel module of PowerVR GPU"
LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=8c2810fa6bfdc5ae5c15a0c1ade34054 \
"
inherit module
DEPENDS += "linux-renesas"
PN = "kernel-module-gles"
PR = "r0"

COMPATIBLE_MACHINE = "x5h_vpf|x5h_evb|ironhide|perceptor"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require include/rcar-gfx-common.inc

SRC_URI:rcar-gen5-evb = "${GFX_URL}/raw/${BRANCH}/gfxdrv/GSX_KM_X5H.tar.bz2;\
sha256sum=3f00b6d29ba994bc4c0a19c966997a69a1aa9a104cae7eec578c3bd2d85c131f"

SRC_URI:rcar-gen5-vpf = "${GFX_URL}/raw/${BRANCH}/gfxdrv/GSX_KM_X5H.tar.bz2;\
sha256sum=3aa00b7ec70ec4fd1a976a82943a8e8e3ab6c5d3cd52ebd09e9a65dab1815861"

S = "${UNPACKDIR}/rogue_km"

KBUILD_DIR = "${S}/build/linux/r8a78000_linux"
KBUILD_OUTDIR = "binary_r8a78000_linux_nullws_drm_release/target_aarch64/kbuild"

B = "${KBUILD_DIR}"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OEMAKE += "CROSS_COMPILE=${CROSS_COMPILE}"
EXTRA_OEMAKE += "KCFLAGS='-Wno-error=type-limits -Wno-type-limits -Wno-error=missing-field-initializers -Wno-missing-field-initializers'"

# Build GFX kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

module_do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC
    cd ${KBUILD_DIR}
    oe_runmake
}

module_do_install() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
    cd ${KBUILD_DIR}
    oe_runmake DISCIMAGE="${D}" install
    rm ${D}/etc/powervr_ddk_install_km.log
    # Blacklist pvrsrvkm
    install -d ${D}${sysconfdir}/modprobe.d/
    echo "blacklist pvrsrvkm" >> ${D}${sysconfdir}/modprobe.d/gles.conf

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
    ${sysconfdir}/modprobe.d/gles.conf \
"

RPROVIDES:${PN} += "kernel-module-pvrsrvkm"

INSANE_SKIP:append = " buildpaths"

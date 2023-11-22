DESCRIPTION = "Kernel module of PowerVR GPU"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=8c2810fa6bfdc5ae5c15a0c1ade34054 \
"
inherit module
DEPENDS += "linux-renesas"
PN = "kernel-module-gles"
PR = "r0"

COMPATIBLE_MACHINE = "whitehawk"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require include/rcar-gfx-common.inc

SRC_URI = " \
    ${GFX_URL}/raw/${BRANCH}/gfxdrv/GSX_KM_V4H.tar.bz2 \
    file://blacklist.conf \
"
SRC_URI[sha256sum] = "4d3749f0bcc8f7319fb2225fd6d3da9bacebd90f8a8ee6a48d4994f2e4ac5fd1"

S = "${WORKDIR}/rogue_km"

KBUILD_DIR = "${S}/build/linux/r8a779g_linux"
KBUILD_OUTDIR = "binary_r8a779g_linux_nullws_drm_release/target_aarch64/kbuild"

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
    install -d ${D}/lib/modules/${KERNEL_VERSION}
    cd ${KBUILD_DIR}
    oe_runmake DISCIMAGE="${D}" install
    rm ${D}/etc/powervr_ddk_install_km.log
    # Install blacklist config file
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 644 ${WORKDIR}/blacklist.conf ${D}${sysconfdir}/modprobe.d/blacklist.conf
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

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/pvrsrvkm.ko \
    ${sysconfdir}/modules-load.d \
    ${sysconfdir}/modprobe.d/blacklist.conf \
"

RPROVIDES_${PN} += "kernel-module-pvrsrvkm"

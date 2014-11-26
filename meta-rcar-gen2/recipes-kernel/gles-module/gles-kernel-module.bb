DESCRIPTION = "RGX/SGX kernel module"
LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=60422928ba677faaa13d6ab5f5baaa1e \
                    file://MIT-COPYING;md5=8c2810fa6bfdc5ae5c15a0c1ade34054"
DEPENDS = "linux-renesas"
PN = "gles-kernel-module"
PR = "r0"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI_r8a7790 = 'file://RGX_KM_H2.tar.bz2'
S_r8a7790 = "${WORKDIR}/rogue_km"
KERNEL_SRC_PATH_r8a7790 = "build/linux/r8a7790_linux"
TARGET_PATH_r8a7790 = "rogue_km/binary_r8a7790_linux_release/target/kbuild/"

SRC_URI_r8a7791 = 'file://SGX_KM_M2.tar.bz2'
S_r8a7791 = "${WORKDIR}/eurasia_km"
KERNEL_SRC_PATH_r8a7791 = "eurasiacon/build/linux2/r8a7791_linux/"
TARGET_PATH_r8a7791 = "eurasia_km/eurasiacon/binary2_r8a7791_linux_release/target/kbuild"

SRC_URI_r8a7793 = 'file://SGX_KM_M2.tar.bz2'
S_r8a7793 = "${WORKDIR}/eurasia_km"
KERNEL_SRC_PATH_r8a7793 = "eurasiacon/build/linux2/r8a7793_linux/"
TARGET_PATH_r8a7793 = "eurasia_km/eurasiacon/binary2_r8a7793_linux_release/target/kbuild"

SRC_URI_r8a7794 = 'file://SGX_KM_E2.tar.bz2'
S_r8a7794 = "${WORKDIR}/eurasia_km"
KERNEL_SRC_PATH_r8a7794 = "eurasiacon/build/linux2/r8a7794_linux/"
TARGET_PATH_r8a7794 = "eurasia_km/eurasiacon/binary2_r8a7794_linux_release/target/kbuild"

GLES = "${@base_contains('MACHINE_FEATURES', 'rgx', 'rgx', \
    base_contains('MACHINE_FEATURES', 'sgx', 'sgx', '', d), d)}"

RPROVIDES_${PN} += "${GLES}-kernel-module"

inherit module
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_populate_lic[noexec] = "1"

export BUILDDIR = "${STAGING_INCDIR}/.."
export LIBSHARED = "${STAGING_LIBDIR}"
export KERNELSRC = "${STAGING_KERNEL_DIR}"
export CROSS_COMPILE = "${TARGET_PREFIX}"
export KERNELDIR = "${STAGING_KERNEL_DIR}"
export LDFLAGS=""
export CP = "cp"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_compile() {
    cd ${S}/${KERNEL_SRC_PATH}
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    make kbuild ARCH=arm DISCIMAGE=${D}
}

do_install() {
    export DISCIMAGE=${D}
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}

    cd ${S}/${KERNEL_SRC_PATH}

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    make kbuild_install ARCH=arm DISCIMAGE=${D}
    mkdir -p  ${D}/usr/src/kernel/include
    cp -f ${WORKDIR}/${TARGET_PATH}/Module.symvers \
        ${KERNELSRC}/include/${GLES}.symvers
    cp -f ${WORKDIR}/${TARGET_PATH}/Module.symvers \
        ${D}/usr/src/kernel/include/${GLES}.symvers
}

do_clean_source() {
	rm -Rf ${KERNELSRC}/include/${GLES}.symvers
}

ALLOW_EMPTY_kernel-module-bc-example = "1"
ALLOW_EMPTY_kernel-module-dc-linuxfb = "1"
ALLOW_EMPTY_kernel-module-pvrsrvkm = "1"

FILES_${PN}-dev = " \
    /usr/src/kernel/include/${GLES}.symvers \
"

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/* \
    /lib/modules/${KERNEL_VERSION}/extra/* \
"

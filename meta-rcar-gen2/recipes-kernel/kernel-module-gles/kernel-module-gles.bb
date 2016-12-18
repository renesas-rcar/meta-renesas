DESCRIPTION = "RGX/SGX kernel module"

require include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=60422928ba677faaa13d6ab5f5baaa1e \
    file://MIT-COPYING;md5=8c2810fa6bfdc5ae5c15a0c1ade34054 \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-gles"
PR = "r0"

COMPATIBLE_MACHINE = "(blanche|wheat)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI_r8a7792 = 'file://SGX_KM_V2H.tar.bz2 \
           file://0001-adopt-rzg_m-module-to-kernel-4.4.6.patch \
           file://0002-make-driver-renesas-compatible.patch \
	   file://0004-adopt-gles-module-to-4.8.patch \
          '
S_r8a7792 = "${WORKDIR}/eurasia_km"
KERNEL_SRC_PATH_r8a7792 = "eurasiacon/build/linux2/r8a7792_linux/"
TARGET_PATH_r8a7792 = "eurasia_km/eurasiacon/binary2_r8a7792_linux_release/target/kbuild"

GLES = "${@base_contains('MACHINE_FEATURES', 'rgx', 'rgx', \
    base_contains('MACHINE_FEATURES', 'sgx', 'sgx', '', d), d)}"

RPROVIDES_${PN} += "${GLES}-kernel-module"

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
}

RPROVIDES_${PN} += "kernel-module-pvrsrvkm kernel-module-dc-linuxfb kernel-module-bc-example"

DESCRIPTION = "OP-TEE Linux Driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"
inherit module

PN = "optee-linuxdriver"
PR = "r0"
BRANCH = "master"
SRC_URI = " \
    git://github.com/OP-TEE/optee_linuxdriver.git;branch=${BRANCH} \
    file://0001-add-optee_linuxdriver-R-Car-support.patch \
"
SRCREV = "f9779c6095dd2e2f492e27a6d79f2c766d3e5714"

PV = "1.0.0+renesas+git${SRCPV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

LDFLAGS[unexport] = "1"

do_configure[noexec] = "1"
# We need to be staged before do_complie. This recipe does not execute do_configure.
do_complile[depends] += "virtual/kernel:do_shared_workdir"

do_compile() {
    # Build kernel module
    cd ${S}/
    make -C ${KBUILD_OUTPUT} M=${S} modules
}

do_install() {
    # Create shared directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${STAGING_KERNEL_DIR}/include
    install -d ${STAGING_KERNEL_DIR}/include/arm_common
    install -d ${STAGING_KERNEL_DIR}/include/linux

    # Install kernel module
    install -m 0644 ${S}/core/optee.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${S}/armtz/optee_armtz.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/


    # Install shared library to STAGING_KERNEL_DIR for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/Module.symvers ${STAGING_KERNEL_DIR}/include/optee.symvers

    # Install shared header files to STAGING_KERNEL_DIR
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/include/arm_common/optee_msg.h ${STAGING_KERNEL_DIR}/include/arm_common/
    install -m 0644 ${S}/include/arm_common/optee_smc.h ${STAGING_KERNEL_DIR}/include/arm_common/
    install -m 0644 ${S}/include/linux/tee_client_api.h ${STAGING_KERNEL_DIR}/include/linux/
    install -m 0644 ${S}/include/linux/tee_core.h ${STAGING_KERNEL_DIR}/include/linux/
    install -m 0644 ${S}/include/linux/tee_ioc.h ${STAGING_KERNEL_DIR}/include/linux/
    install -m 0644 ${S}/include/linux/tee_kernel_api.h ${STAGING_KERNEL_DIR}/include/linux/
}

PACKAGES = "\
    ${PN} \
    ${PN}-armtz \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/optee.ko \
"

FILES_${PN}-armtz = " \
    /lib/modules/${KERNEL_VERSION}/extra/optee_armtz.ko \
"

RPROVIDES_${PN} += "optee-linuxdriver"
RPROVIDES_${PN} += "kernel-module-optee"
RPROVIDES_${PN} += "kernel-module-optee-armtz"

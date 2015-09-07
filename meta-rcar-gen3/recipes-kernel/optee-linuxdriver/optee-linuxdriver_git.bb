DESCRIPTION = "OP-TEE Linux Driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"
inherit module

PN = "optee-linuxdriver"
PR = "r0"
BRANCH = "master"
SRC_URI = \
    "git://github.com/OP-TEE/optee_linuxdriver.git;protocol=https;branch=${BRANCH}"
SRCREV = "dc1c7ac1b22c86a2f346845aaaed5bc416e3577b"

PV = "0.3.0+renesas+git${SRCPV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

LDFLAGS[unexport] = "1"

do_compile() {
    # Build kernel module
    cd ${S}/
    make -C ${KBUILD_OUTPUT} M=${S} modules
}

do_install() {
    # Create shared folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/
    mkdir -p ${D}/usr/src/kernel/include
    mkdir -p ${D}/usr/src/kernel/include/arm_common
    mkdir -p ${D}/usr/src/kernel/include/linux

    # Copy kernel module
    install -m 0644 ${S}/core/optee.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${S}/armtz/optee_armtz.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Copy shared header files
    install -m 0644 ${S}/Module.symvers ${STAGING_KERNEL_DIR}/include/optee.symvers
    install -m 0644 ${S}/include/arm_common/teesmc.h ${D}/usr/src/kernel/include/arm_common
    install -m 0644 ${S}/include/arm_common/teesmc_st.h ${D}/usr/src/kernel/include/arm_common
    install -m 0644 ${S}/include/linux/tee_client_api.h ${D}/usr/src/kernel/include/linux
    install -m 0644 ${S}/include/linux/tee_core.h ${D}/usr/src/kernel/include/linux
    install -m 0644 ${S}/include/linux/tee_ioc.h ${D}/usr/src/kernel/include/linux
    install -m 0644 ${S}/include/linux/tee_kernel_api.h ${D}/usr/src/kernel/include/linux
    install -m 0644 ${S}/Module.symvers ${D}/usr/src/kernel/include/optee.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-armtz \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/optee.ko \
"

FILES_${PN}-armtz = " \
    /lib/modules/${KERNEL_VERSION}/extra/optee_armtz.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include \
    /usr/src/kernel/include/arm_common \
    /usr/src/kernel/include/arm_common/*.h \
    /usr/src/kernel/include/linux \
    /usr/src/kernel/include/linux/*.h \
    /usr/src/kernel/include/optee.symvers  \
"

RPROVIDES_${PN} += "optee-linuxdriver"
RPROVIDES_${PN} += "kernel-module-optee"
RPROVIDES_${PN} += "kernel-module-optee-armtz"

do_configure[noexec] = "1"

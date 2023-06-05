DESCRIPTION = "QoS driver for S4"

require include/rcar-bsp-modules-common.inc

LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-qos"
PR = "r0"

QOS_DRV_URL = "git://github.com/renesas-rcar/qos_drv.git;protocol=https"
BRANCH = "rcar-gen3"
SRCREV = "ffbd6d68968a0b0989c3ffb1eabc9b536215fcee"

SRC_URI = "${QOS_DRV_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
QOS_DRV_DIR = "qos-module/files/qos/drv"

includedir = "${RENESAS_DATADIR}/include"

# Build Qos kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    cd ${S}/${QOS_DRV_DIR}/
    oe_runmake
}

do_install () {
    # Create destination directories
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/${QOS_DRV_DIR}/Module.symvers ${KERNELSRC}/include/qos.symvers

    # Install kernel module
    install -m 644 ${S}/${QOS_DRV_DIR}/qos.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared header files
    install -m 644 ${S}/${QOS_DRV_DIR}/qos.h ${KERNELSRC}/include/
    install -m 644 ${S}/${QOS_DRV_DIR}/qos_public_common.h ${KERNELSRC}/include/
    install -m 644 ${S}/${QOS_DRV_DIR}/qos_public_common.h ${D}/${includedir}/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/qos.ko \
"

FILES:${PN}-dev = " \
    /include/qos_public_common.h \
"

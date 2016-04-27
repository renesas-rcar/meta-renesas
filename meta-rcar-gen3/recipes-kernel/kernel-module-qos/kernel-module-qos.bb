DESCRIPTION = "QoS driver for the R-Car Gen3"

require include/rcar-gen3-modules-common.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=0ebf15a927e436cec699371cd890775c \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-qos"
PR = "r0"

QOS_DRV_URL = "git://github.com/renesas-rcar/qos_drv.git"
BRANCH = "rcar-gen3"
SRCREV = "a3d7db9e8090646816e3981656f8581b040be4a5"

SRC_URI = "${QOS_DRV_URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
QOS_DRV_DIR = "qos-module/files/qos/drv"

# do_configure() nothing
do_configure[noexec] = "1"

do_compile() {
    cd ${S}/${QOS_DRV_DIR}/
    oe_runmake
}

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/usr/src/kernel/include

    # Copy shared library for reference from other modules
    install -m 644 ${S}/${QOS_DRV_DIR}/Module.symvers ${KERNELSRC}/include/qos.symvers
    install -m 644 ${S}/${QOS_DRV_DIR}/Module.symvers ${D}/usr/src/kernel/include/qos.symvers

    # Copy kernel module
    install -m 644 ${S}/${QOS_DRV_DIR}/qos.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/qos.ko \
"

FILES_${PN}-dev = " \
    /usr/src/kernel/include/qos.symvers \
"

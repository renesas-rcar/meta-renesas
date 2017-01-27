DESCRIPTION = "Linux ICCOM hwspinlock Sample Driver for Renesas R-Car Gen3"

require include/rcar-gen3-modules-common.inc
require include/iccom-control.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module distro_features_check

DEPENDS = "linux-renesas"
PN = "kernel-module-iccom-hwspinlock-sample"
PR = "r0"

REQUIRED_DISTRO_FEATURES = "iccom"

SRC_URI = "file://iccom-hwspinlock-sample.tar.bz2"

S = "${WORKDIR}/iccom-hwspinlock-sample"
B = "${S}/iccom-hwspinlock-sample"

do_install () {
    # Create destination directory
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install kernel module
    install -m 644 ${B}/iccom_hwspinlock_sample.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/iccom_hwspinlock_sample.ko \
"

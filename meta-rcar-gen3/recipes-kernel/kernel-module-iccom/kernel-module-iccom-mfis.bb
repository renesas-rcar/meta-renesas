DESCRIPTION = "Linux ICCOM MFIS Driver for Renesas R-Car Gen3"

require include/rcar-gen3-modules-common.inc
require include/iccom-control.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module distro_features_check

DEPENDS = "linux-renesas"
PN = "kernel-module-iccom-mfis"
PR = "r0"

REQUIRED_DISTRO_FEATURES = "iccom"

SRC_URI = "file://iccom-mfis.tar.bz2"

S = "${WORKDIR}/iccom-mfis"
B = "${S}/iccom-mfis/drv"

do_install () {
    # Create destination directory
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install kernel module
    install -m 644 ${B}/iccom_mfis.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/iccom_mfis.ko \
"

# Autoload ICCOM MFIS Driver
KERNEL_MODULE_AUTOLOAD = "iccom_mfis"

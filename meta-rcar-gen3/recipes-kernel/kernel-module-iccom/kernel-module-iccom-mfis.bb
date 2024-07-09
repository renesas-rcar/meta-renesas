DESCRIPTION = "Linux ICCOM MFIS Driver for Renesas R-Car Gen3"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

require include/rcar-gen3-modules-common.inc

inherit module features_check

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "iccom"

PN = "kernel-module-iccom-mfis"
PR = "r0"

SRC_URI = "file://iccom-mfis.tar.bz2"

S = "${WORKDIR}/iccom-mfis"
B = "${S}/iccom-mfis/drv"

# Build ICCOM MFIS kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_install () {
    # Create destination directory
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install kernel module
    install -m 644 ${B}/iccom_mfis.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/iccom_mfis.ko \
"

# Autoload ICCOM MFIS Driver
KERNEL_MODULE_AUTOLOAD:append = " iccom_mfis"

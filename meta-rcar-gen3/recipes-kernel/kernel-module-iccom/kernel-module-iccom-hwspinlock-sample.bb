DESCRIPTION = "Linux ICCOM hwspinlock Sample Driver for Renesas R-Car Gen3"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

require include/rcar-gen3-modules-common.inc

inherit module features_check

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "iccom"

PN = "kernel-module-iccom-hwspinlock-sample"
PR = "r0"

SRC_URI = "file://iccom-hwspinlock-sample.tar.bz2"

S = "${WORKDIR}/iccom-hwspinlock-sample"
B = "${S}/iccom-hwspinlock-sample"

# Build ICCOM kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_install () {
    # Create destination directory
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install kernel module
    install -m 644 ${B}/iccom_hwspinlock_sample.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/iccom_hwspinlock_sample.ko \
"

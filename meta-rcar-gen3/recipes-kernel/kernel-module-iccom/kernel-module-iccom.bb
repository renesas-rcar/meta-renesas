DESCRIPTION = "ICCOM kernel module for Renesas R-Car Gen3"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

require include/rcar-gen3-modules-common.inc
inherit module

COMPATIBLE_MACHINE = "salvator-x"

DEPENDS = "linux-renesas"
PN = "kernel-module-iccom"
PR = "r0"

SRC_URI = "file://linux_iccom_driver.tar.gz"
S = "${WORKDIR}/linux_iccom_driver"

SRC_URI:append = " \
    file://0001-iccom-drv-iccom-support-linux-v5.patch \
"

do_compile() {
    # Build kernel module
    cd ${S}/
    make -C ${KBUILD_OUTPUT} M=${S} modules
}

do_install () {
    # Create destination directory
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${KERNELSRC}/include
    install -d ${KERNELSRC}/include/linux

    # Install kernel module
    install -m 644 ${B}/iccom.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared library to STAGING_KERNEL_DIR for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/Module.symvers ${KERNELSRC}/include/iccom.symvers

    # Install shared header files to STAGING_KERNEL_DIR
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/public/iccom.h ${KERNELSRC}/include/linux/iccom.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/iccom.ko \
"

FILES:${PN}-dbg = ""
ALLOW_EMPTY:${PN}-dbg = "1"
INSANE_SKIP:${PN}-dbg = "buildpaths"

RPROVIDES:${PN} += "kernel-module-iccom-${KERNEL_VERSION}"

# Autoload ICCOM Driver
# KERNEL_MODULE_AUTOLOAD:append = "iccom"

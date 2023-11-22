DESCRIPTION = "Memory Manager Buffer Kernel module for Renesas R-Car Gen3"

require mmngr_drv.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngrbuf"
PR = "r0"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git/mmngr_drv/mmngrbuf/mmngrbuf-module/files/mmngrbuf/drv"

# Support HW overlay mode
SRC_URI:append = "\
    file://0001-mmngr_drv-mmngrbuf-Take-care-of-any-migration-of-sca.patch \
"

includedir = "${RENESAS_DATADIR}/include"
SSTATE_ALLOW_OVERLAP_FILES += "${STAGING_INCDIR}"

# Build Memory Manager Buffer kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile:prepend() {
    install -d ${INCSHARED}
}

do_install () {
    # Create destination directories
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${B}/Module.symvers ${KERNELSRC}/include/mmngrbuf.symvers

    # Install kernel module
    install -m 644 ${B}/mmngrbuf.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${B}/../include/mmngr_buf_private.h ${KERNELSRC}/include/
    install -m 644 ${B}/../include/mmngr_buf_private_cmn.h ${KERNELSRC}/include/

    # Install shared header files to ${includedir}
    install -m 644 ${B}/../include/mmngr_buf_private_cmn.h ${D}/${includedir}/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/mmngrbuf.ko \
"

FILES:${PN}-dbg = "" 
ALLOW_EMPTY:${PN}-dbg = "1" 

RPROVIDES:${PN} += "kernel-module-mmngrbuf"

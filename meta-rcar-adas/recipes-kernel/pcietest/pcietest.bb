DESCRIPTION = "PCIe test tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "linux-renesas"

S = "${WORKDIR}"

SRC_URI = "file://ep_setup.sh"

do_compile() {
    cd ${STAGING_KERNEL_DIR}
    make -C tools/pci
}

do_install () {
    cd ${STAGING_KERNEL_DIR}
    make -C tools/pci install DESTDIR=${D}
    install -d ${D}${bindir}
    install -m 755 ${S}/ep_setup.sh ${D}${bindir}/
}


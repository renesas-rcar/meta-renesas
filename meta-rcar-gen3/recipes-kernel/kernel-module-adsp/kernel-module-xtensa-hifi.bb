DESCRIPTION = "ADSP Driver for Linux"
LICENSE = "GPLv2 & MIT"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=55979d94ccbb973fdea772250e0c54d6 \
    file://MIT-COPYING;md5=f932f6ad0feea4f97b6e8316e172070e \
"

require include/adsp-control.inc
inherit module

FILESEXTRAPATHS_prepend := "${THISDIR}/xtensa-hifi:"

SRC_URI = "${@base_conditional('USE_ADSP', '1', 'file://RCG3AHPDL4001ZDO.tar.gz', '', d)}"

S = "${WORKDIR}/RCG3AHPDL4001ZDO"

# Define the extra config for using module.bbclass to build ADSP driver
EXTRA_OEMAKE_append = " KDIR=${STAGING_KERNEL_DIR}"

do_install_append(){
    # Create install directories
    install -d ${D}/${includedir}
    install -d ${D}/${includedir}/sys/xt-shmem

    # Install shared header files
    install -m 644 ${S}/include/*.h ${D}/${includedir}/
    install -m 644 ${S}/include/sys/xt-shmem/*.h ${D}/${includedir}/sys/xt-shmem/
}

# The ADSP driver need adsp firmware for running
RDEPENDS_${PN}_append = " adsp-fw-module"

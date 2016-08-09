DESCRIPTION = "ADSP Driver for Linux"
LICENSE = "GPLv2 & MIT"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=55979d94ccbb973fdea772250e0c54d6 \
    file://MIT-COPYING;md5=f932f6ad0feea4f97b6e8316e172070e \
"

require include/adsp-options.inc
inherit module

FILESEXTRAPATHS_prepend := "${THISDIR}/xtensa-hifi:"

SRC_URI = "${@base_conditional('USE_ADSP', '1', 'file://RCG3AHPDL4001ZDO.tar.gz', '', d)}"

S = "${WORKDIR}/RCG3AHPDL4001ZDO"

# Define the extra config for using module.bbclass to build ADSP driver
EXTRA_OEMAKE_append = " KDIR=${STAGING_KERNEL_DIR}"

# The ADSP driver need adsp firmware for running
RDEPENDS_${PN}_append = " adsp-fw-module"

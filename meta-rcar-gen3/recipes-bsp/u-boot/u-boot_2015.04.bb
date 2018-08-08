require recipes-bsp/u-boot/u-boot-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc
require include/uboot-control.inc

DEPENDS += "dtc-native"

UBOOT_URL = "git://github.com/renesas-rcar/u-boot.git"
BRANCH = "v2015.04/rcar-3.7.0"

SRC_URI = "${UBOOT_URL};branch=${BRANCH}"
SRCREV = "6a82c94590cda5d61720798979bdf0144b9569b9"

PV = "v2015.04+git${SRCPV}"

# Add u-boot options for H3
EXTRA_OEMAKE_append_r8a7795 = " ${@get_uboot_config_opt(d)}"

UBOOT_SREC ?= "u-boot-elf.srec"
UBOOT_SREC_IMAGE ?= "u-boot-elf-${MACHINE}-${PV}-${PR}.srec"
UBOOT_SREC_SYMLINK ?= "u-boot-elf-${MACHINE}.srec"

do_deploy_append() {
    install -m 644 ${B}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
    cd ${DEPLOYDIR}
    rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
}

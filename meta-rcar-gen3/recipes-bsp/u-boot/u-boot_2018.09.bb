require u-boot-common_${PV}.inc
require u-boot.inc
require include/uboot-control.inc

DEPENDS += "bc-native dtc-native"

UBOOT_URL = "git://github.com/renesas-rcar/u-boot.git"
BRANCH = "v2018.09/rcar-3.8.0"

SRC_URI = "${UBOOT_URL};branch=${BRANCH}"
SRCREV = "70206a1b84e6e35c33b3760fae6a2a6dbe6ce534"
PV = "v2018.09+git${SRCPV}"

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

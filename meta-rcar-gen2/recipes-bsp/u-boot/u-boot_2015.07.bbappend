
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://0001-workaround-gcc-optimisation.patch \
	file://0002-add-skrzg1m-and-r8a7743.patch \
	file://0003-fix-da9063-address.patch \
	file://0004-reduce-speed-on-i2c.patch \
	file://0005-add-skrzg1e-and-r8a7745.patch \
"

PATCHTOOL_rzg1 = "git"

UBOOT_SREC ?= "u-boot.srec"
UBOOT_SREC_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.srec"
UBOOT_SREC_SYMLINK ?= "u-boot-${MACHINE}.srec"


do_deploy_append() {
    install -m 644 ${S}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
    cd ${DEPLOYDIR}
    rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
}

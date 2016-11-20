require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

UBOOT_URL = "git://git.denx.de/u-boot-sh.git"
BRANCH = "rmobile"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "${UBOOT_URL};branch=${BRANCH}"
SRCREV = "798dc6be7feabce33676877f85d307f571a9ec15"
SRC_URI_append = " \
    file://0001-Wheat-board-support.patch \
    file://0002-mmc-fix-RMOBILE-ARCH.patch \
"

PV = "rmobile+git${SRCPV}"

EXTRA_OEMAKE_append = " KCFLAGS=-fgnu89-inline"

UBOOT_SREC ?= "u-boot-elf.gsrec"
UBOOT_SREC_IMAGE ?= "u-boot-elf-${MACHINE}-${PV}-${PR}.srec"
UBOOT_SREC_SYMLINK ?= "u-boot-elf-${MACHINE}.srec"

do_deploy_append() {
    install -m 644 ${S}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
    cd ${DEPLOYDIR}
    rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
}

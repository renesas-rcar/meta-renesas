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
    file://0003-Wheat-add-SPL-and-QoS.patch \
    file://0004-gen2-set-default-baudrate-115200.patch \
    file://0005-wheat-support-ftb-booting.patch \
"

PV = "rmobile+git${SRCPV}"

EXTRA_OEMAKE_append = " KCFLAGS=-fgnu89-inline"

UBOOT_IMG ?= "u-boot.img"
UBOOT_IMG_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_IMG_SYMLINK ?= "u-boot-${MACHINE}.img"

UBOOT_SPL ?= "u-boot-spl.bin"
UBOOT_SPL_IMAGE ?= "u-boot-spl-${MACHINE}-${PV}-${PR}.bin"
UBOOT_SPL_SYMLINK ?= "u-boot-spl-${MACHINE}.bin"

do_deploy_append_rcar-gen2() {
    install -m 644 ${S}/spl/${UBOOT_SPL} ${DEPLOYDIR}/${UBOOT_SPL_IMAGE}
    cd ${DEPLOYDIR}
    rm -f ${UBOOT_SPL} ${UBOOT_SPL_SYMLINK}
    ln -sf ${UBOOT_SPL_IMAGE} ${UBOOT_SPL_SYMLINK}
    ln -sf ${UBOOT_SPL_IMAGE} ${UBOOT_SPL}

    install -m 644 ${S}/${UBOOT_IMG} ${DEPLOYDIR}/${UBOOT_IMG_IMAGE}
    cd ${DEPLOYDIR}
    rm -f ${UBOOT_IMG} ${UBOOT_IMG_SYMLINK}
    ln -sf ${UBOOT_IMG_IMAGE} ${UBOOT_IMG_SYMLINK}
    ln -sf ${UBOOT_IMG_IMAGE} ${UBOOT_IMG}
}

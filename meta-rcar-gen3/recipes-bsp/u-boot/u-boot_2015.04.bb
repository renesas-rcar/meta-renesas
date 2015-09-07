require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

SRCREV = "f33cdaa4c3da4a8fd35aa2f9a3172f31cc887b35"

PV = "v2015.04+git${SRCPV}"

EXTRA_OEMAKE_append = " KCFLAGS=-fgnu89-inline"

SRC_URI += " \
    file://0001-ehci-hcd-fix-warnings-on-64-bit-builds.patch \
    file://0002-usb_storage-Fix-USB-storage-capacity-detection-on-64.patch \
    file://0003-usb-ehci-Add-rcar_gen3-support.patch \
    file://0004-board-renesas-Add-salvatorx-support.patch \
    file://0005-armv8-rcar_gen3-Add-base-code.patch \
    file://0006-armv8-rcar_gen3-Add-s_init-call-at-lowlevel_init.patch \
    file://0007-armv8-rcar_gen3-Remove-SMP-code-of-initializetion.patch \
    file://0008-configs-salvatorx-Disable-icache-and-dcache.patch \
    file://0009-armv8-Add-ISB-after-cpacr_el1-update.patch \
    file://0010-serial-Add-r8a7795-support.patch \
    file://0011-board-renesas-salvatorx-Fix-serial-clock-supply.patch \
    file://0012-net-ravb-Add-r8a7795-support.patch \
    file://0013-net-ravb-Fix-Ethernet-AVB-network-connection.patch \
    file://0014-net-ravb-Fix-ethernet-speed-limit-to-100BASE-T.patch \
    file://0015-configs-rcar_gen3-Add-CONFIG_CMD_BOOTI.patch \
    file://0016-configs-rcar_gen3-Add-define-CONFIG_SUPPORT_RAW_INIT.patch \
    file://0017-configs-salvatorx-Fix-generic-interrupt-controller-b.patch \
    file://0018-board-renesas-salvatorx-Fix-memory-area-configuratio.patch \
    file://0019-arm-rcar_gen3-Add-skip-of-relocation.patch \
    file://0020-board-renesas-salvatorx-Add-force-power-and-clock-su.patch \
    file://0021-net-eth-Fix-case-of-set-mac-addr-after-boot.patch \
    file://0022-usb-ehci-rcar_gen3-Fix-usb-storage-detect-and-read.patch \
"

do_compile_append() {
    ${OBJCOPY} -I binary -O srec --adjust-vma=0x49000000 \
    ${B}/u-boot.bin ${B}/u-boot.srec
}

UBOOT_SREC ?= "u-boot.srec"
UBOOT_SREC_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.srec"
UBOOT_SREC_SYMLINK ?= "u-boot-${MACHINE}.srec"

do_deploy_append() {
    install ${S}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
    cd ${DEPLOYDIR}
    rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
    ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
}

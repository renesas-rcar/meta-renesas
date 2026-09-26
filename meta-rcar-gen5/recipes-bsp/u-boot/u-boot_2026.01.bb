HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
PE = "1"

require recipes-bsp/u-boot/u-boot.inc
require include/rcar-kernel-info-common.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/../../recipes-kernel/linux/linux-renesas:"

DEPENDS += "flex-native bison-native bc-native dtc-native lzop-native srecord-native gnutls-native"

UBOOT_URL = "git://github.com/renesas-rcar/u-boot.git;protocol=https"
BRANCH = "v2026.01/rcar-8.0.0"

SRC_URI = "${UBOOT_URL};branch=${BRANCH}"
SRC_URI:append:rcar-gen5-evb = " file://localversion_auto.cfg"

SRC_URI:append:rcar-gen5-evb = " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'rcar-firmware', \
        'file://rcar_gen5_mp_phy.bin', '', d)}"

SRCREV = "4d6512f23ab2ff5af7ba9c426147b4a549d4798a"

PV = "v2026.01+git${SRCPV}"

UBOOT_SREC_SUFFIX = "srec"
UBOOT_SREC ?= "u-boot-elf.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_IMAGE ?= "u-boot-elf-${MACHINE}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_SYMLINK ?= "u-boot-elf-${MACHINE}.${UBOOT_SREC_SUFFIX}"

EXTRA_OEMAKE += "LOCALVERSION='-SDK${SDK_VERSION}'"

# Apply the generated firmware config fragment
do_configure:append:rcar-gen5-evb() {
    if ${@bb.utils.contains('MACHINE_FEATURES', 'rcar-firmware', 'true', 'false', d)}; then
        install -d ${WORKDIR}/firmware
        install -m 644 ${UNPACKDIR}/rcar_gen5_mp_phy.bin ${WORKDIR}/firmware/

        echo "CONFIG_PHY_R8A78000_MP_PHY_FW=\"${WORKDIR}/firmware/rcar_gen5_mp_phy.bin\"" \
            > ${WORKDIR}/rcar_uboot_fw.cfg
        if [ -n "${UBOOT_CONFIG}" ]
        then
            for config in ${UBOOT_MACHINE}; do
                i=$(expr $i + 1);
                for type in ${UBOOT_CONFIG}; do
                    j=$(expr $j + 1);
                    if [ $j -eq $i ]
                    then
                        type=${type#*_}
                        merge_config.sh -m -O ${B}/${config}-${type} ${B}/${config}-${type}/.config ${WORKDIR}/rcar_uboot_fw.cfg
                    fi
                done
                unset j
            done
            unset i
        else
            merge_config.sh -m ${B}/.config ${WORKDIR}/rcar_uboot_fw.cfg
        fi
    fi
}

do_deploy:append() {
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    type=${type#*_}
                    install -m 644 ${B}/${config}-${type}/${UBOOT_SREC} ${DEPLOYDIR}/u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}
                    cd ${DEPLOYDIR}
                    ln -sf u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX} u-boot-elf-${type}.${UBOOT_SREC_SUFFIX}
                fi
            done
            unset j
        done
        unset i
    else
        install -m 644 ${B}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
        cd ${DEPLOYDIR}
        rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
        ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
        ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
    fi
}

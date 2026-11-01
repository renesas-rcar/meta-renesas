HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"
PE = "1"

S = "${WORKDIR}/git"

require u-boot.inc

DEPENDS += "flex-native bison-native bc-native dtc-native lzop-native srecord-native"

UBOOT_URL = "git://github.com/renesas-rcar/u-boot.git;protocol=https"
BRANCH = "${@oe.utils.conditional("RGID_ON", "1", "rcar-6.0.0.rc13/rgid.rc5", "v2022.01/rcar-6.0.0.rc13", d )}"

SRC_URI = "${UBOOT_URL};branch=${BRANCH}"
SRCREV = "${@oe.utils.conditional("RGID_ON", "1", "6481799d1b6e2b3e4041f546794ac2621f30de1c", "02594c3836a4e1bcfa73f1776d61ae01f77c5f04", d )}"
PV = "v2022.01+git${SRCPV}"

SRC_URI:append:r8a779g0 = " \
    ${@oe.utils.conditional("RGID_ON", "1", "file://0001-configs-whitehawk-rgid-Increase-LMB_MAX_REGIONS-on-V.patch","", d )} \
"

SUPPORT_LPM = " \
    file://0001-Revert-ARM-renesas-Disable-relocation-on-R-Car-Gen3.patch \
    file://0002-arm64-booti-grayhawk-Retain-kernel-image-at-original.patch \
"

SRC_URI:append:r8a779h0 = " ${@oe.utils.conditional("LPM_ON", "1", "${SUPPORT_LPM}", "", d )}"

UBOOT_SREC_SUFFIX = "srec"
UBOOT_SREC ?= "u-boot-elf.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_IMAGE ?= "u-boot-elf-${MACHINE}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_SYMLINK ?= "u-boot-elf-${MACHINE}.${UBOOT_SREC_SUFFIX}"

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
                    install -m 644 ${B}/${config}/${UBOOT_SREC} ${DEPLOYDIR}/u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}
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

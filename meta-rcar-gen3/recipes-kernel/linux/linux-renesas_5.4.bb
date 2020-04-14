DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require include/avb-control.inc
require include/iccom-control.inc
require recipes-kernel/linux/linux-yocto.inc
require include/cas-control.inc
require include/adsp-control.inc

COMPATIBLE_MACHINE = "salvator-x|h3ulcb|m3ulcb|m3nulcb|ebisu"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4/rcar-3.10.0.rc1"
SRCREV = "52be90f101cd18160cda57d246c1e2e6abfe4a74"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "5.4.0"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

SRC_URI_append = " \
    file://touch.cfg \
    ${@oe.utils.conditional("USE_AVB", "1", " file://usb-video-class.cfg", "", d)} \
"

# Enable RPMSG_VIRTIO depend on ICCOM
SUPPORT_ICCOM = " \
    file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch \
    file://iccom.cfg \
"

SRC_URI_append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", "${SUPPORT_ICCOM}", "", d)} \
"

# Add SCHED_DEBUG config fragment to support CAS
SRC_URI_append = " \
    ${@oe.utils.conditional("USE_CAS", "1", " file://capacity_aware_migration_strategy.cfg", "",d)} \
"

# Add ADSP ALSA driver
SUPPORT_ADSP_ASOC = " \
    file://0001-ADSP-add-document-for-compatible-string-renesas-rcar.patch \
    file://0002-ADSP-add-ADSP-sound-driver-source.patch \
    file://0003-ADSP-add-build-for-ADSP-sound-driver.patch \
    file://0004-ADSP-integrate-ADSP-sound-for-H3-M3-M3N-board.patch \
    file://0005-ADSP-integrate-ADSP-sound-for-E3-board.patch \
    file://0006-ADSP-remove-HDMI-support-from-rcar-sound.patch \
    file://adsp.cfg \
"

SRC_URI_append = " \
    ${@oe.utils.conditional("USE_ADSP", "1", "${SUPPORT_ADSP_ASOC}", "", d)} \
"


DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require include/avb-control.inc
require include/iccom-control.inc
require recipes-kernel/linux/linux-yocto.inc
require include/cas-control.inc

COMPATIBLE_MACHINE = "salvator-x|h3ulcb|m3ulcb|m3nulcb|ebisu"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.4.72/rcar-4.1.0.rc2"
SRCREV = "77f691b28600cfdc66ce17541e05e9ffe0c918d0"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "5.4.72"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

SRC_URI_append = " \
    file://touch.cfg \
    ${@bb.utils.contains('MACHINE_FEATURES','usb3','file://usb3.cfg','',d)} \
    ${@oe.utils.conditional("USE_AVB", "1", " file://usb-video-class.cfg", "", d)} \
"

# Enable RPMSG_VIRTIO depend on ICCOM
SUPPORT_ICCOM = " \
    file://iccom.cfg \
"

SRC_URI_append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", "${SUPPORT_ICCOM}", "", d)} \
"

# Add SCHED_DEBUG config fragment to support CAS
SRC_URI_append = " \
    ${@oe.utils.conditional("USE_CAS", "1", " file://capacity_aware_migration_strategy.cfg", "",d)} \
"

# Install USB3.0 firmware
KERNEL_FIRMWARE_URI = "https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/plain"
USB3_FIRMWARE_V2 = "${KERNEL_FIRMWARE_URI}/r8a779x_usb3_v2.dlmem;md5sum=645db7e9056029efa15f158e51cc8a11"
USB3_FIRMWARE_V3 = "${KERNEL_FIRMWARE_URI}/r8a779x_usb3_v3.dlmem;md5sum=687d5d42f38f9850f8d5a6071dca3109"

SRC_URI_append = " \
    ${USB3_FIRMWARE_V2} \
    ${USB3_FIRMWARE_V3} \
    ${@bb.utils.contains('MACHINE_FEATURES','usb3','file://usb3.cfg','',d)} \
"

do_download_firmware () {
    install -d ${STAGING_KERNEL_DIR}/firmware
    install -m 755 ${WORKDIR}/r8a779x_usb3_v*.dlmem ${STAGING_KERNEL_DIR}/firmware
}

addtask do_download_firmware after do_configure before do_compile


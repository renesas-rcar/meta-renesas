DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require include/avb-control.inc
require include/iccom-control.inc
require recipes-kernel/linux/linux-yocto.inc
require include/cas-control.inc
require include/adsp-control.inc

COMPATIBLE_MACHINE = "salvator-x|h3ulcb|m3ulcb|m3nulcb|ebisu|draak"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "${@oe.utils.conditional("USE_SAFE_RENDERING", "1", "rcar-5.1.4.rc3/saferendering.rc9", "v5.10.218/rcar-5.3.3", d)}"
SRCREV = "${@oe.utils.conditional("USE_SAFE_RENDERING", "1", \
    "e2037726e5f6c3d6de6bc7d78b50ea9e2248a00d", \
    "a6b0abad730dc65042dcfd2802386b2aa11f0063", d)}"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH};protocol=https"

LINUX_VERSION ?= "5.10.218"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

SRC_URI:append = " \
    file://touch.cfg \
    ${@oe.utils.conditional("USE_AVB", "1", " file://usb-video-class.cfg", "", d)} \
"

# Add module.lds
SRC_URI:append = " \
    file://0001-scripts-Add-module.lds-to-fix-out-of-tree-modules-bu.patch \
"

# Enable RPMSG_VIRTIO; device tree H3/M3 depend on ICCOM
SUPPORT_ICCOM = " \
    file://iccom.cfg \
    file://0001-arm64-dts-renesas-r8a77951-Support-ICCOM.patch \
    file://0001-arm64-dts-renesas-r8a77961-Support-ICCOM.patch \
"

SRC_URI:append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", "${SUPPORT_ICCOM}", "", d)} \
"

# Add SCHED_DEBUG config fragment to support CAS
SRC_URI:append = " \
    ${@oe.utils.conditional("USE_CAS", "1", " file://capacity_aware_migration_strategy.cfg", "",d)} \
"

# Add ADSP ALSA driver
SUPPORT_ADSP_ASOC = " \
    file://0001-Add-ADSP-sound-driver-source.patch \
    file://0002-Add-document-file-for-ADSP-sound-driver.patch \
    file://0003-ADSP-remove-HDMI-support-from-rcar-sound.patch \
    file://0004-ADSP-integrate-ADSP-sound-for-H3-M3-M3N-board.patch \
    file://0005-ADSP-add-build-for-ADSP-sound-driver.patch \
    file://0006-ADSP-integrate-ADSP-sound-for-E3-board.patch \
    file://adsp.cfg \
"

SRC_URI:append = " \
    ${@oe.utils.conditional("USE_ADSP", "1", "${SUPPORT_ADSP_ASOC}", "", d)} \
"

# Install USB3.0 firmware to rootfs
USB3_FIRMWARE_V2 = "https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/plain/r8a779x_usb3_v2.dlmem;md5sum=645db7e9056029efa15f158e51cc8a11"
USB3_FIRMWARE_V3 = "https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/plain/r8a779x_usb3_v3.dlmem;md5sum=687d5d42f38f9850f8d5a6071dca3109"

SRC_URI:append = " \
    ${USB3_FIRMWARE_V2} \
    ${USB3_FIRMWARE_V3} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'usb3', 'file://usb3.cfg', 'file://disable_fw_loader_user_helper.cfg', d)} \
"

# Work-around to fix perf build error
SRC_URI:append = " file://init_disassemble_info-signature-changes-causes-compile-failures.patch"

do_download_firmware () {
    install -d ${STAGING_KERNEL_DIR}/firmware
    install -m 755 ${WORKDIR}/r8a779x_usb3_v*.dlmem ${STAGING_KERNEL_DIR}/firmware
}

addtask do_download_firmware after do_configure before do_compile

do_src_package_preprocess () {
        # Trim build paths from comments in generated sources to ensure reproducibility
        sed -i -e "s,${S}/,,g" \
               -e "s,${B}/,,g" \
            ${B}/drivers/video/logo/logo_linux_clut224.c \
            ${B}/drivers/tty/vt/consolemap_deftbl.c \
            ${B}/lib/oid_registry_data.c
}
addtask do_src_package_preprocess after do_compile before do_install

do_compile_kernelmodules:append () {
    if (grep -q -i -e '^CONFIG_MODULES=y$' ${B}/.config); then
        # 5.10+ kernels have module.lds that we need to copy for external module builds
        if [ -e "${B}/scripts/module.lds" ]; then
            install -Dm 0644 ${B}/scripts/module.lds ${STAGING_KERNEL_BUILDDIR}/scripts/module.lds
        fi
    fi
}

do_deploy:append() {
    # Remove the redundant device tree file (<device_tree>-<MACHINE>.dtb) that was created in the deploy directory
    for dtbf in ${KERNEL_DEVICETREE}; do
        dtb=`normalize_dtb "$dtbf"`
        dtb_ext=${dtb##*.}
        dtb_base_name=`basename $dtb .$dtb_ext`
        rm -f $deployDir/$dtb_base_name-${KERNEL_DTB_LINK_NAME}.$dtb_ext
    done
}

# uio_pdrv_genirq configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'


require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the R-Mobile board"
COMPATIBLE_MACHINE = "armadillo800eva"

PV_append = "+git${SRCREV}"

FILESEXTRAPATHS_prepend_armadillo800eva := "${THISDIR}/${PN}:"
SRCREV = "db30987713a6fdcb215500864a5ffdf76e8dfe41"
SRC_URI = "git://github.com/renesas-devel/linux.git;branch=3.4.81-ltsi"

SRC_URI_armadillo800eva += "file://defconfig \
	file://0001-ASoC-fsi-fixup-pm_runtime_disable-timing-on-fsi_prob.patch \
	file://0002-ASoC-fsi-tidyup-remove-un-necessary-operation-from-f.patch \
	file://0003-ASoC-fsi-convert-to-devm_xxx.patch \
	file://0004-ASoC-fsi-use-devm_request_irq.patch \
	file://0005-ASoC-fsi-fsi_set_master_clk-was-called-from-fsi_hw_x.patch \
	file://0006-ASoC-fsi-care-fsi_hw_start-stop-return-value.patch \
	file://0007-ASoC-fsi-add-master-clock-control-functions.patch \
	file://0008-LTSI-ASoC-fsi-backport-fix-for-devm_clk_get.patch \
	file://0009-ASoC-fsi-tidyup-FSIA-B-settings.patch \
	file://0010-ASoC-fsi-tidyup-sh_fsi_platform_info-pointer.patch \
	file://0011-ARM-shmobile-use-FSI-driver-s-audio-clock-on-armadil.patch \
	file://0012-ARM-shmobile-armadillo800eva-set-clock-rates-before-.patch \
	file://0013-usb-renesas_usbhs-gadget-add-support-for-set_selfpow.patch \
	file://0014-usb-renesas_usbhs-fixup-DMA-transport-data-alignment.patch \
	file://0015-usb-renesas_usbhs-gadget-add-usb_gadget_ops-pullup-s.patch \
	file://0016-ARM-shmobile-armadillo800eva-enable-restart.patch \
	file://0017-usb-renesas_usbhs-fixup-interrupt-status-clear-metho.patch \
	file://0018-armadillo800eva-Add-clock-entry-for-SGX540.patch \
	file://0019-armadillo800eva-Add-2DDMAC-UIO-device.patch \
	file://0020-Increase-VPU-memory-to-100MB.patch \
	file://0021-shmobile-ipmmu-pmb-Unlock-mutex-before-exited-when-P.patch \
	file://0022-dma-shdma-workaround-clear-unnecessary-irq-flags.patch \
	file://0023-workqueue-reorder-queueing-functions-so-that-_on-var.patch \
	file://0024-workqueue-make-queueing-functions-return-bool.patch \
	file://0025-workqueue-disable-irq-while-manipulating-PENDING.patch \
	file://0026-ASoC-wm8978-enable-symmetric-rates.patch \
	file://0027-Update-Meram-Errata.patch \
	file://0028-fbdev-sh_mobile_lcdc-fixup-B-side-hsync-adjust-setti.patch \
	file://0029-ARM-shmobile-armadillo800eva-__io-abuse-cleanup.patch \
	file://0030-USB-Add-EHCI-and-OHCI-for-Renesas-R-Mobile-SoC-r8a77.patch \
	file://0031-ARM-shmobile-r8a7740-Add-clock-configuration-for-USB.patch \
	file://0032-ARM-shmobile-armadillo800eva-Add-USB-ECHI-and-OHCI-s.patch \
	file://0001-uio-Fix-memory-size-check-with-vma-in-uio_mmap_physi.patch \
	file://0001-ASoC-fsi-reserve-prefetch-period-on-DMA-transferring.patch \
"

S = "${WORKDIR}/git"
# default cmd line. 
CMDLINE = "console=tty0 console=ttySC1,115200 earlyprintk=sh-sci.1,115200 \
	ignore_loglevel root=/dev/nfs ip=dhcp nfsroot=,rsize=4096,wsize=4096 rw"

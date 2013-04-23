FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION = "3.4.25"

DESCRIPTION = "Linux kernel for the bockw board"
COMPATIBLE_MACHINE_bockw = "bockw"
KBRANCH_DEFAULT_bockw = "standard/bockw"
KBRANCH_bockw = "${KBRANCH_DEFAULT}"
KMACHINE_bockw = "bockw"

PR = "r0"
PV_append = "+git${SRCREV}"

SRCREV_machine_bockw ?= "4938ff7a961bcf44ef53c4a928f6cf9c4e6ddb4d"

SRC_URI_append_bockw = " \
	file://defconfig \
	\
	file://0001-Local-ARM-shmobile-add-gic_iid-macro-for-ICCIAR-inte.patch \
	file://0002-Local-ARM-shmobile-add-R8A7778-clock-support.patch \
	file://0003-Local-ARM-shmobile-add-R8A7778-intc-support.patch \
	file://0004-Local-ARM-shmobile-add-R8A7778-setup-support.patch \
	file://0005-Local-ARM-shmobile-add-R-Car-M1A-Bock-W-platform-sup.patch \
	file://0006-Local-ARM-shmobile-bockw-add-SMSC-Eth-support.patch \
	\
	file://0001-drm-Move-drm_format_num_planes-to-drm_crtc.c.patch \
	file://0002-drm-Add-drm_format_plane_cpp-utility-function.patch \
	file://0003-drm-Add-drm_format_-horz-vert-_chroma_subsampling-ut.patch \
	file://0004-drm-Add-sanity-checks-to-framebuffer-creation.patch \
	file://0005-drm-Fix-EDID-color-format-parsing.patch \
	file://0006-drm-Parse-color-format-information-in-CEA-blocks.patch \
	file://0007-drm-add-the-VIC-number-to-the-CEA-EDID-modes.patch \
	file://0008-drm-add-DRM_MODE_FLAG_DBLCLK-to-CEA-modes-requiring-.patch \
	file://0009-drm-edid-Document-drm_mode_find_dmt.patch \
	file://0010-drm-edid-Rewrite-drm_mode_find_dmt-search-loop.patch \
	file://0011-drm-edid-Allow-drm_mode_find_dmt-to-hunt-for-reduced.patch \
	file://0012-drm-edid-Remove-a-misleading-comment.patch \
	file://0013-drm-edid-s-drm_gtf_modes_for_range-drm_dmt_modes_for.patch \
	file://0014-drm-edid-Add-the-reduced-blanking-DMT-modes-to-the-D.patch \
	file://0015-drm-edid-Fix-some-comment-typos-in-the-DMT-mode-list.patch \
	file://0016-drm-edid-Do-drm_dmt_modes_for_range-for-all-range-de.patch \
	file://0017-drm-edid-Give-the-est3-mode-struct-a-real-name.patch \
	file://0018-drm-edid-Add-extra_modes.patch \
	file://0019-drm-edid-Generate-modes-from-extra_modes-for-range-d.patch \
	file://0020-drm-edid-add-missing-NULL-checks.patch \
	file://0021-drm-replace-open-coded-ARRAY_SIZE-with-macro.patch \
	file://0022-drm-edid-Add-a-workaround-for-1366x768-HD-panel.patch \
	file://0023-drm-Unify-and-fix-idr-error-handling.patch \
	file://0024-drm-edid-Try-harder-to-fix-up-base-EDID-blocks.patch \
	file://0025-drm-Store-vendor-IDs-directly-in-the-EDID-quirk-stru.patch \
	file://0026-drm-edid-fix-collision-between-two-patches-breaking-.patch \
	file://0027-drm-kms-reduce-some-messages-to-debug-level-v2.patch \
	file://0028-drm-add-drm_property_change_is_valid.patch \
	file://0029-drm-WARN-when-drm_connector_attach_property-fails.patch \
	file://0030-drm-create-struct-drm_object_properties-and-use-it.patch \
	file://0031-drm-add-generic-ioctls-to-get-set-properties-on-any-.patch \
	file://0032-drm-make-the-connector-properties-code-use-the-objec.patch \
	file://0033-drm-add-count-to-struct-drm_object_properties.patch \
	file://0034-drm-add-CRTC-properties.patch \
	file://0035-drm-Don-t-initialize-local-ret-variable-when-not-nee.patch \
	file://0036-drm-Miscellaneous-typo-fixes-and-documentation-updat.patch \
	file://0037-drm-Constify-drm_mode_config_funcs-pointer.patch \
	file://0038-drm-Make-the-CRTC-gamma_set-operation-optional.patch \
	file://0039-drm-add-bitmask-property-type.patch \
	file://0040-drm-add-plane-properties.patch \
	file://0041-drm-Constify-params-to-format_check-and-framebuffer_.patch \
	file://0042-drm-Use-stdint-types-for-consistency.patch \
	file://0043-drm-edid-quirks-ViewSonic-VA2026w.patch \
	file://0044-drm-edid-Make-the-header-fixup-threshold-tunable.patch \
	file://0045-drm-increase-DRM_OBJECT_MAX_PROPERTY-to-24.patch \
	file://0046-drm-edid-Don-t-add-inferred-modes-with-higher-resolu.patch \
	file://0047-drm-fb-helper-delay-hotplug-handling-when-partially-.patch \
	file://0048-drm-fb-helper-don-t-call-drm_crtc_helper_set_config.patch \
	file://0049-drm-remove-the-list_head-from-drm_mode_set.patch \
	file://0050-drm-edid-Fix-potential-memory-leak-in-edid_load.patch \
	file://0051-drm-fb-helper-don-t-clobber-output-routing-in-setup_.patch \
	file://0052-drm-Remove-two-unused-fields-from-struct-drm_display.patch \
	file://0053-Documentation-DocBook-DRM-framework-documentation.patch \
	file://0054-drm-remove-the-raw_edid-field-from-struct-drm_displa.patch \
	file://0055-drm-Initialize-object-type-when-using-DRM_MODE-macro.patch \
	file://0056-drm-Add-misssing-static-storage-class-specifier-in-d.patch \
	file://0057-drm-Add-EDID_QUIRK_FORCE_REDUCED_BLANKING-for-ASUS-V.patch \
	file://0058-drm-fb-helper-don-t-call-drm_helper_connector_dpms-d.patch \
	file://0059-drm-consistently-name-interlaced-modes.patch \
	file://0060-drm-edid-Add-some-bounds-checking.patch \
	file://0061-drm-edid-Add-bounds-checking-to-HDMI-VSDB-parsing.patch \
	file://0062-drm-edid-Refactor-HDMI-VSDB-detection.patch \
	file://0063-drm-edid-add-support-for-E-DDC.patch \
	file://0064-drm-edid-limit-printk-when-facing-bad-edid.patch \
	file://0065-DRM-Add-DRM-GEM-CMA-helper.patch \
	file://0066-drm-Add-NV24-and-NV42-pixel-formats.patch \
	file://0067-drm-refcnt-drm_framebuffer-v4.1.patch \
	file://0068-drm-support-for-rotated-scanout.patch \
	file://0069-drm-Export-drm_probe_ddc.patch \
	file://0070-UAPI-Refer-to-the-DRM-UAPI-headers-with-.-and-from-c.patch \
	file://0071-drm-rename-drm_dp_i2c_helper.c-to-drm_dp_helper.c.patch \
	file://0072-drm-dp-helper-extract-drm_dp_channel_eq_ok.patch \
	file://0073-drm-Be-more-paranoid-with-integer-overflows.patch \
	file://0074-drm-Constify-some-function-arguments.patch \
	file://0075-drm-Ignore-blob-propertys-in-drm_property_change_is_.patch \
	file://0076-drm-add-helper-to-sort-panels-to-the-head-of-the-con.patch \
	file://0077-drm-fb_helper-Fix-checkpatch-errors.patch \
	file://0078-drm-fb_helper-Convert-printk-to-pr_-and-dev_.patch \
	file://0079-drm-drm_fb_helper-Remove-unnecessary-braces.patch \
	file://0080-drm-get-cea-video-id-code-for-a-given-display-mode.patch \
	file://0081-drm-fix-documentation-for-drm_crtc_set_mode.patch \
	file://0082-drm-Reject-addfb2-with-undefined-flag-bits-set.patch \
	file://0083-drm-crtc-Remove-redundant-NULL-check-before-kfree.patch \
	file://0084-drm-crtc-Fix-potential-NULL-pointer-dereference.patch \
	file://0085-drm-fb_helper-Fix-potential-NULL-pointer-dereference.patch \
	file://0086-drm-add-drm_send_vblank_event-helper-v5.patch \
	file://0087-drm-extract-drm_kms_helper_hotplug_event.patch \
	file://0088-drm-handle-HPD-and-polled-connectors-separately.patch \
	file://0089-drm-run-the-hpd-irq-event-code-directly.patch \
	file://0090-drm-properly-init-reset-connector-status.patch \
	file://0091-drm-don-t-start-the-poll-engine-in-probe_single_conn.patch \
	file://0092-drm-don-t-poll-forced-connectors.patch \
	file://0093-drm-use-memchr_inv.patch \
	file://0094-drm-don-t-unnecessarily-enable-the-polling-work.patch \
	file://0095-drm-edid-tune-down-debug-message-in-parse_hdmi_vsdb.patch \
	file://0096-drm-doc-Helpers-are-not-a-Midlayer.patch \
	file://0097-drm-doc-integrate-crtc-helper-api-into-docbook.patch \
	file://0098-drm-doc-integrate-fb-helper-reference-into-docs.patch \
	file://0099-drm-doc-add-new-dp-helpers-into-drm-DocBook.patch \
	file://0100-drm-add-drm_mode_cea_vic.patch \
	file://0101-drm-remove-legacy-drm_connector_property-fxns.patch \
	file://0102-drm-edid-Update-range-descriptor-struct-for-EDID-1.4.patch \
	file://0103-drm-edid-Add-packed-attribute-to-new-gtf2-and-cvt-st.patch \
	file://0104-DRM-Add-DRM-KMS-FB-CMA-helper.patch \
	file://0105-drm-Make-the-.mode_fixup-operations-mode-argument-a-.patch \
	file://0106-drm-Constify-gem_vm_ops-pointer.patch \
	file://0107-devres-Add-devres_release.patch \
	file://0108-drm-Renesas-R-Car-Display-Unit-DRM-driver.patch \
	file://0109-ARM-mach-shmobile-r8a7778-GPIO-PFC-support.patch \
	file://0110-ARM-7376-1-clkdev-Implement-managed-clk_get.patch \
	file://0111-ARM-7534-1-clk-Make-the-managed-clk-functions-generi.patch \
	file://0112-ARM-7537-1-clk-Fix-release-in-devm_clk_put.patch \
	file://0113-ARM-shmobile-bockw-Add-Display-Unit-support.patch \
	file://0114-ARM-mach-shmobile-r8a7778-add-DIV4-clock-support.patch \
	file://0115-ARM-mach-shmobile-r8a7778-Add-Display-Unit-clock-sup.patch \
	file://0116-ARM-shmobile-bockw-invoke-multipled-pins-control-ini.patch \
	file://0117-drm-gp2d-DRM-Support-for-Renesas-R-Car-GP2D.patch \
	file://0118-ARM-mach-shmobile-r8a7778-add-GP2D-clock-support.patch \
	file://0119-ARM-shmobile-bockw-add-R-Car-GP2D-device-support.patch \
	file://0120-drm-gp2d-support-AAFC-and-JUMP-command.patch \
	file://0121-dma-buf-mmap-support.patch \
	file://0122-common-dma-mapping-introduce-dma_get_sgtable-functio.patch \
	file://0123-dma-buf-add-vmap-interface.patch \
	file://0124-drm-GEM-CMA-Split-object-creation-into-object-alloc-.patch \
	file://0125-drm-GEM-CMA-Add-DRM-PRIME-support.patch \
	file://0126-drm-rcar-du-Use-drm_encoder_cleanup-directly-as-.des.patch \
	file://0127-drm-rcar-du-Enable-alpha-blending-support.patch \
	file://0128-drm-rcar-du-Don-t-re-reserve-hardware-plane-at-each-.patch \
	file://0129-drm-rcar-du-Fix-planes-allocation-for-multiplanar-fo.patch \
	file://0130-drm-rcar-du-Add-DRM-PRIME-support.patch \
	file://0131-drm-rcar-du-Fix-race-condition-between-page-flip-req.patch \
	file://0132-drm-rcar-du-Add-configurable-z-order-support-for-pla.patch \
	file://0133-drm-rcar-du-Support-configurable-color-keying-for-pl.patch \
	file://0134-drm-rcar-du-Update-plane-format-after-releasing-hard.patch \
	file://0135-drm-rcar-du-Fix-register-access-for-global-registers.patch \
	file://0136-drm-rcar-du-Fix-plane-index-wrap-around-for-multi-pl.patch \
	file://0137-gp2d-work-queue-timeout-fix-event-dealloc-fix-was-ke.patch \
	file://0138-gp2d-add-some-supports-for-R-GP2D-acceleration.patch \
	file://0001-ARM-dma-mapping-add-support-for-dma_get_sgtable.patch \
	file://0002-drm-rcar-du-Enable-the-DE-signal.patch \
	file://0003-drm-rcar-du-Split-hardware-and-KMS-planes.patch \
	file://0004-drm-rcar-du-Add-support-for-the-second-CRTC.patch \
	file://0005-drm-rcar-du-Name-the-encoder-platform-data-union.patch \
	file://0006-drm-rcar-du-Fix-crash-when-disabling-an-already-disa.patch \
	file://0007-drm-rcar-du-Prepare-unprepare-clock.patch \
	file://0008-drm-rcar-du-Centralize-DU-device-core-resource-manag.patch \
	file://0009-drm-rcar-du-Reorganize-CRTC-start-stop-and-power-man.patch \
	file://0010-drm-rcar-du-Create-common-encoder-and-connector-stru.patch \
	file://0011-drm-rcar-du-Add-support-for-cloned-mode-on-DU1.patch \
	file://0012-ARM-shmobile-bockw-Update-to-the-new-DU-platform-dat.patch \
	file://0013-drm-rcar-du-Add-FBDEV-emulation-support.patch \
	file://0014-ARM-shmobile-bockw-fix-R-GP2D-interrupt-number.patch \
	file://0015-drm-gp2d-suppress-debug-logs-output.patch \
        \
	file://0001-Local-ARM-shmobile-bockw-Add-suport-mtd-info.patch \
        file://0002-Local-ARM-shmobile-bockw-Add-support-SDHI0.patch \
        file://0003-Local-ARM-shmobile-bockw-Add-support-USBH.patch \
        file://0001-i2c-add-Renesas-R-Car-I2C-driver.patch \
        file://0002-Local-ARM-shmobile-r8a7778-add-I2C-clock-support.patch \
        file://0003-Local-ARM-shmobile-r8a7778-add-I2C-platform-support.patch \
        file://0004-Local-ARM-shmobile-bockw-Add-suport-RTC-device.patch \
        file://0005-spi-spi-sh-hspi-drop-frees-of-devm_-alloc-d-data.patch \
        file://0006-spi-sh-hspi-add-CS-manual-control-support.patch \
        file://0007-spi-sh-hspi-fix-return-value-check-in-hspi_probe.patch \
        file://0008-Local-ARM-shmobile-r8a7778-add-HSPI-clock-support.patch \
        file://0009-Local-ARM-shmobile-r8a7778-add-hspi-platform-support.patch \
        file://0010-Local-ARM-shmobile-bockw-Add-suport-hspi-device.patch \
        file://0011-spi-hspi-fixup-long-delay-time.patch \
	\
	file://add_gp2d_drm_kbuild.patch \
        \
        file://0001-ARM-shmobile-r8a7778-add-ICB-clock-support.patch \
        file://0002-ARM-shmobile-bockw-enable-ICB-clock.patch \
        file://0003-ARM-shmobile-r8a7778-add-IPMMU-support.patch \
        file://0004-ARM-shmobile-r8a7778-add-VPC-support.patch \
        file://0005-ARM-shmobile-r8a7778-add-MERAM-support.patch \
        file://0006-ARM-shmobile-r8a7778-add-ICB-MERAM-work-around.patch \
        file://0007-ARM-shmobile-bockw-invoke-ICB-MERAM-workaround.patch \
        file://0008-ARM-shmobile-r8a7778-add-VIO-support.patch \
        file://0009-ARM-shmobile-r8a7778-add-VPU-support.patch \
        file://0010-ARM-shmobile-r8a7778-add-SSB-workaround.patch \
        file://0011-ARM-shmobile-bockw-invoke-SSB-workaround.patch \
        file://0012-Local-ARM-shmobile-r8a7778-add-MMC-clock-support.patch \
        file://0013-Local-ARM-shmobile-r8a7778-add-mmcif-platform-suppor.patch \
        file://0014-mmc-sh-mmcif-support-single-irq-variant.patch \
        file://0015-Local-ARM-shmobile-bockw-Change-MMC-Card-detect.patch \
        file://0016-Local-ARM-shmobile-r8a7778-Fix-INT-num-for-I2C1-2-3.patch \
	"

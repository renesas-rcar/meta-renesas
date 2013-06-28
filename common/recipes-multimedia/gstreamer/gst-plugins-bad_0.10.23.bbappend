DEPENDS += "gstreamer directfb faad2 libxml2 libuiomux libshvio"

EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-directfb', '--enable-directfb')}"
EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"

EXTRA_OECONF += "--with-plugins=h264parse"

TARGET_CFLAGS += "-D_GNU_SOURCE"

PRINC := "${@int(PRINC) + 5}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
    file://0001-directfb-trick-hardware-acceleration-check.patch \
    file://0002-rawparse-set-a-frame-number-when-beginning-playback.patch \
    file://0003-rawparse-send-the-seek-event-without-check-if-backwa.patch \
    file://0004-ext-dfbvideosink-use-libshvio-instead-of-software-re.patch \
    file://0005-ext-dfbvideosink-give-more-color-formats-which-can-b.patch \
    file://0006-ext-dfbvideosink-ignore-alpha-channel-in-source-data.patch \
    file://0007-ext-dfbvideosink-fix-array-index-at-the-source-pc-ad.patch \
    file://0008-dfb-example-support-scaling-and-position-change-for-.patch \
    file://0009-dfb-example-force-UYVY-pixelformat.patch \
    file://0010-dfb-example-support-specifying-a-display-layer-with-.patch \
    file://0011-dfb-example-add-a-option-for-specifying-the-number-o.patch \
    file://0012-dfb-example-support-video-image-cropping.patch \
    file://0013-dfb-example-change-a-default-value-of-queue-size-for.patch \
    file://0014-ext-dfbvideosink-enable-scaling-if-libshvio-blitting.patch \
    file://0015-ext-dfbvideosink-control-image-aspect-ratio-in-scali.patch \
    file://0016-ext-dfbvideosink-handle-the-query-of-stride-supporte.patch \
    file://0017-ext-dfbvideosink-use-rowstride-to-render-with-libshv.patch \
    file://0018-ext-dfbvideosink-pass-a-dummy-pointer-as-the-3rd-arg.patch \
    file://0019-dfb-example-add-a-option-for-ignoring-image-s-aspect.patch \
    file://0020-ext-dfbvideosink-compensate-for-the-interpretation-d.patch \
    file://0021-ext-dfbvideosink-set-mask-fields-and-endianness-for-.patch \
    file://0022-ext-dfbvideosink-fix-creating-a-caps-list.patch \
    file://0023-ext-dfbvideosink-change-the-order-of-color-formats-f.patch \
    file://0024-ext-dfbvideosink-support-NV12-color-format-for-caps.patch \
    file://0025-ext-dfbvideosink-eliminate-a-warning-message.patch \
    file://0026-ext-dfbvideosink-fix-a-calcutaion-in-the-conversion-.patch \
    file://0027-ext-dfbvideosink-set-the-CbCr-plane-address-on-semi-.patch \
    file://0028-ext-dfbvideosink-fix-a-wrong-calculation-in-top-addr.patch \
    file://0029-ext-dfbvideosink-disable-signal-handler-owned-by-Dir.patch \
    file://0030-ext-dfbvideosink-set-byte-offset-to-chroma-plane-wit.patch \
    file://0031-h264parse-add-start-code-prefix-property.patch \
    file://0032-h264parse-set-GST_BUFFER_FLAG_PREROLL-flag-if-buffer.patch \
    file://0033-Revert-h264parse-also-handle-3-byte-bytestream-sync-.patch \
    file://0034-h264parse-also-handle-3-byte-bytestream-sync-code.patch \
    file://0035-h264parse-ignore-codec_data-if-stream-format-byte-st.patch \
    file://0036-h264parse-parse-codec_data-only-if-stream-format-is-.patch \
    file://0037-Revert-directfb-trick-hardware-acceleration-check.patch \
    file://0038-Revert-ext-dfbvideosink-ignore-alpha-channel-in-sour.patch \
    file://0039-ext-dfbvideosink-enable-double-buffering-for-primary.patch \
    file://0040-ext-dfbvideosink-don-t-call-SetConfiguration-when-te.patch \
    file://0041-ext-dfbvideosink-invoke-SetConfiguration-regardless-.patch \
    file://0042-gst-h264parse-prioritize-legacyh264parse-over-h264pa.patch \
    file://0043-dfb-video-example-a-sample-of-video-playback-applica.patch \
    file://0044-ext-dfbvideosink-allow-to-specify-a-sub-region-in-th.patch \
    file://0045-ext-dfbvideosink-post-a-message-that-means-frames-be.patch \
    file://0046-dfb-video-example-add-playback-rate-option.patch \
    file://0047-dfb-video-example-break-out-of-event-loop-when-playb.patch \
    file://0048-dfb-video-example-properly-update-playback-rate-to-1.patch \
    file://0049-dfb-video-example-Don-t-seek-after-every-frame-in-sl.patch \
    file://0050-gst-h264parse-only-check-out-of-order-timestamps-on-.patch \
    file://0051-dfb-video-example-support-quick-seeking-option-witho.patch \
    file://0052-dfb-video-example-add-create_video_pipeline-function.patch \
    file://0053-dfb-video-example-create-a-single-stream-video-only-.patch \
    file://0054-dfb-video-example-insert-a-queue-plugin-into-video-p.patch \
    file://0055-dfb-video-example-support-audio-playback.patch \
    file://0056-ext-dfbvideosink-assign-ICB-MERAM-as-readahead-cache.patch \
    file://0057-ext-dfbvideosink-use-tile-width-for-accessing-tiled-.patch \
    file://0058-ext-dfbvideosink-convert-interlaced-video-fields-int.patch \
    file://0059-ext-dfbvideosink-handle-the-query-of-tladdressing-su.patch \
    file://0060-dfb-example-dfb-video-example-Move-directfb.h-includ.patch \
    file://0061-ext-dfbvideosink-assign-ICB-MERAM-as-writeback-cache.patch \
    file://0062-ext-dfbvideosink-adjust-alignment-of-output-image-s-.patch \
    file://0063-dfb-example-clear-another-surface-of-double-buffer.patch \
    file://0064-dfb-example-resize-an-image-without-sub-surface.patch \
    file://0065-dfb-example-fix-lacks-of-finalizing-processing.patch \
    file://0066-dfb-video-example-set-playback-rate-as-specified-whe.patch \
    file://0067-ext-dfbvideosink-deal-with-rowstride-as-byte-unit.patch \
    file://0068-ext-dfbvideosink-properly-read-non-16-byte-aligned-b.patch \
    file://0069-ext-dfbvideosink-use-next_field_offset-for-interlace.patch \
    file://0070-ext-dfbvideosink-access-tiled-image-with-tile-bounda.patch \
    file://0071-ext-dfbvideosink-reflect-tile-boundary-offset-on-MEx.patch \
    file://0072-dfb-video-example-support-video-image-cropping.patch \
    file://0073-Revert-ext-dfbvideosink-adjust-alignment-of-output-i.patch \
    file://0074-ext-dfbvideosink-lock-MERAM-to-share-a-region-with-o.patch \
    file://0075-ext-dfbvideosink-properly-draw-an-image-to-non-16-by.patch \
    file://0076-ext-dfbvideosink-fix-race-condition-in-the-dfbvideos.patch \
    file://0077-ext-dfbvideosink-clear-MERAM-whenever-non-16-byte-al.patch \
    file://0078-ext-dfbvideosink-make-sure-that-the-last-line-render.patch \
    file://0079-ext-dfbvideosink-clear-another-surface-of-double-buf.patch \
    file://0080-ext-dfbvideosink-add-a-function-that-sets-up-MERAM-w.patch \
    file://0081-ext-dfbvideosink-fix-the-incorrect-conversion-from-b.patch \
    file://0082-ext-dfbvideosink-calculate-Y-plane-stride-when-the-c.patch \
    file://0083-ext-dfbvideosink-fix-missing-uiomux_unregister-to-pr.patch \
    file://0084-ext-dfbvideosink-fix-condition-of-the-MERAM-clearing.patch \
    file://0085-ext-dfbvideosink-clear-the-target-surface-whenever-t.patch \
    file://0086-ext-dfbvideosink-terminate-the-setup-immediately-if-.patch \
    file://0087-ext-dfbvideosink-look-for-available-ICBs-when-needed.patch \
    file://0088-h264parse-set-a-preroll-flag-when-config-nals-are-re.patch \
    file://0089-h264parse-flush-the-last-nal-in-the-GstAdapter-when-.patch \
    file://0090-dfb-video-example-remove-duplicate-test-to-determine.patch \
    file://0091-dfb-video-example-fix-incorrect-log-messages.patch \
    file://0092-dfb-example-dfb-video-example-fix-some-lacks-of-obje.patch \
    file://0093-ext-dfbvideosink-add-new-properties-to-specify-color.patch \
    file://0094-dfb-example-dfb-video-example-remove-clearing-a-surf.patch \
    file://0095-ext-dfbvideosink-add-a-new-property-to-specify-the-l.patch \
    file://0096-ext-dfbvideosink-don-t-use-the-cursor-in-the-exclusi.patch \
    file://0097-dfb-video-example-fix-a-memory-leaks-caused-by-illeg.patch \
    file://0001-ext-dfbvideosink-Fix-build-faild-when-not-using-shvi.patch \
    file://0002-ext-dfbvideosink-Fix-build-faild-when-not-using-shme.patch \
    file://0001-ext-dfbvideosink-fix-a-destination-address-of-CbCr-p.patch \
    file://0002-ext-dfbvideosink-recreate-surface-after-changing-the.patch \
    file://0001-ext-dfbvideosink-keep-ICB-indexes-corresponding-to-t.patch \
    file://0002-ext-dfbvideosink-ICB-errata-ICB-transaction-completi.patch \
    file://0003-ext-dfbvideosink-setup-writeback-ICBs-when-set-caps-.patch \
    file://0004-ext-dfbvideosink-clear-MERAM-with-VIO-for-errata-wor.patch \
    file://0005-ext-dfbvideosink-fix-the-incorrect-conversion-from-p.patch \
    file://0006-ext-dfbvideosink-fix-setting-CbCr-plane-physical-add.patch \
    file://0007-ext-dfbvideosink-register-framebuffer-memory-to-UIOM.patch \
    file://0008-ext-dfbvideosink-allocate-a-meram-region-at-the-init.patch \
    file://0009-Revert-ext-dfbvideosink-workaround-recreate-a-surfac.patch \
    file://0001-ext-dfbvideosink-separately-allocate-meram-memory-re.patch \
    "

PRINC := "${@int(PRINC) + 1}"

# file://disable_configure.patch
EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"
# FILES_${PN} += "${sysconfdir}/*"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
        file://0001-headers-update-OMX-headers-version-1.1.1-to-1.1.2.patch \
        file://0001-base_videodec-set-NV12-color-format-as-output-port-d.patch \
        file://0002-base_filter-set-OMX-buffer-flags-if-GST_BUFFER_FLAG_.patch \
        file://0003-base_filter-set-timestamps-if-not-set.patch \
        file://0004-wmvdec-retrieve-parameters-to-decode-VC-1-stream-fro.patch \
        file://0005-wmvdec-kick-off-decoding-VC-1-stream-with-REL-OMX.patch \
        file://0006-base_filter-remove-memory-copying-when-the-buffers-a.patch \
        file://0007-base_videodec-set-rowstride-and-chroma_byte_offset-i.patch \
        file://0008-Re-initialize-OMX-parameter-structure-before-re-use.patch \
        file://0009-Once-reinitialize-an-instance-and-then-retry-OMX_Get.patch \
        file://0010-wmvdec-support-VC-1-advanced-profile.patch \
        file://0011-util-revise-flush-event-sequence.patch \
        file://0012-add-a-choice-of-enabling-disabling-the-post-processi.patch \
        file://0013-base_videodec-send-a-query-whether-the-T-L-addressin.patch \
        file://0014-Revert-base_filter-set-timestamps-if-not-set.patch \
        file://0015-base_videodec-set-tile-width-in-caps-for-T-L-address.patch \
        file://0016-base_filter-videodec-set-next_field_offset-in-caps-f.patch \
        file://0017-base_filter-set-proper-data-size-to-a-GstBuffer-inst.patch \
        file://0018-headers-update-OMX-headers-to-2012-10-05-RC-release-.patch \
        file://0019-base_videodec-set-tile-height-in-caps-for-T-L-addres.patch \
        file://0020-base_videodec-tweak-value-of-row-stride-for-T-L-addr.patch \
        file://0021-Once-reinitialize-an-instance-and-then-retry-OMX_Ini.patch \
        file://0022-util-don-t-skip-waiting-for-state-change-completion-.patch \
        file://0023-util-move-sending-a-pause-command-from-flush_start-t.patch \
        file://0024-util-change-the-beginning-of-locked-section-to-defin.patch \
        file://0025-util-don-t-send-an-omx_state_condition-signal-when-c.patch \
        file://0026-base_filter-set-end-of-frame-flag-for-buffers-contai.patch \
        file://0027-base_filter-overwrite-nFlags-to-OMX_BUFFERFLAG_EOS-w.patch \
        file://0028-util-base_filter-send-an-error-event-to-application-.patch \
        file://0029-mpeg4dec-specify-the-video-stream-type-as-packed-bit.patch \
        file://0030-wmvdec-decrease-the-refcount-of-the-parent-object-of.patch \
        file://0031-base_videodec-add-a-hook-function-for-state-transiti.patch \
        file://0032-base_videodec-fix-missing-uiomux_unregister-to-preve.patch \
        file://0033-base_filter-fix-missing-gst_caps_unref-to-prevent-a-.patch \
        file://0034-wmvdec-support-decoding-VC-1-field-interlaced-images.patch \
        file://0035-base_videodec-free-caps-in-setting_changed_cb.patch \
        file://0036-wmvdec-free-caps-in-gst_omx_wmvdec_sink_setcaps.patch \
        file://0037-wmvdec-improve-field-start-code-search-processing.patch \
        file://0038-mpeg4dec-concatenate-codec_data-with-the-first-frame.patch \
        file://0039-mpeg4dec-retrieve-DCI-from-codec_data.patch \
        file://0001-base_videodec-change-the-tile-height-for-T-L-address.patch \
        file://0001-Stop-using-deprecated-GLib-thread-API.patch \
       	" 

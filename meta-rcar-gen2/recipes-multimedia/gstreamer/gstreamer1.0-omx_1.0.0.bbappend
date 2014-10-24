require ../../include/gles-control.inc

DEPENDS_append_rcar-gen2 = " omx-user-module"
EXTRA_OECONF_append_rcar-gen2 = " --with-omx-target=rcar --enable-experimental"

# Overwrite do_install[postfuncs] += " set_omx_core_name "
# because it will force the plugin to use bellagio instead of our config
revert_omx_core_name() {
    sed -i -e "s;^core-name=.*;core-name=/usr/local/lib/libomxr_core.so;" "${D}/etc/xdg/gstomx.conf"
}
REVERT_OMX_CORE_NAME = ""
REVERT_OMX_CORE_NAME_rcar-gen2 = "revert_omx_core_name"
do_install[postfuncs] += "${REVERT_OMX_CORE_NAME}"

# common
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen2 = " \
    file://0001-omxvideodec-don-t-disable-output-port-but-allocate-o.patch \
    file://0002-omxvideodec-populate-output-buffers-in-the-component.patch \
    file://0003-omxvideodec-update-the-cached-data-of-output-port-de.patch \
    file://0004-omxvideodec-change-supported-color-formats-list-crea.patch \
    file://0005-omxh264dec-allow-to-accept-H.264-stream-in-a-nal-uni.patch \
    file://0006-omxvideodec-set-OMX-buffer-flags-if-GST_BUFFER_FLAG_.patch \
    file://0007-omxvideodec-remove-the-data-packets-left-queued-in-t.patch \
    file://0008-omxwmvdec-support-VC-1-stream-decoding.patch \
    file://0009-omxwmvdec-support-VC-1-advanced-profile.patch \
    file://0010-omxvideodec-don-t-start-the-output-thread-before-set.patch \
    file://0011-omxvideodec-don-t-use-gst_buffer_map-when-copying-to.patch \
    file://0012-gstomx-Once-reinitialize-an-instance-and-then-retry-.patch \
    file://0013-gstomx-fix-a-missing-g_free.patch \
    file://0014-gstomx-Once-reinitialize-an-instance-and-then-retry-.patch \
    file://0015-omxmpeg4videodec-conform-caps-template-to-the-defini.patch \
"

# for wayland
WAYLAND_PATCHES = " \
    file://0016-Revert-omxvideodec-remove-the-data-packets-left-queu.patch \
    file://0017-Revert-omxh264dec-allow-to-accept-H.264-stream-in-a-.patch \
    file://0018-gstomx.conf-use-REL-OMXIL-library-and-give-a-top-pri.patch \
    file://0019-gstomx.conf-Use-REL-OMXIL-library.patch \
    file://0020-gstomx-set-the-default-path-of-gstomx.conf.patch \
    file://0021-omxvideodec-support-zero-copy-buffer-output-to-the-d.patch \
    file://0022-omxvideodec-Fix-incorrect-UV-plane-addressing.patch \
    file://0023-omxvideodec-Generalize-the-output-buffer-creation-fo.patch \
    file://0024-omxvideodec-Group-the-input-buffer-copying-into-a-fu.patch \
    file://0025-omxh264dec-Retrieve-necessary-information-from-codec.patch \
    file://0026-omxh264dec-Support-the-frame-unit-input-into-REL-OMX.patch \
    file://0027-omxvideodec-Don-t-set-the-OMX_BUFFERFLAG_ENDOFFRAME-.patch \
    file://0028-omxh264dec-Remove-parsed-field-from-the-template-cap.patch \
    file://0029-gstomx-Move-output-buffers-sending-into-the-omx-comp.patch \
    file://0030-Support-audiodec-and-aacdec.patch \
    file://0001-omxvideodec-release-frames-with-old-PTS-to-avoid-mem.patch \
    file://0001-omx-fix-two-serious-message-handling-bugs.patch \
"

SRC_URI_append_rcar-gen2 = " \
    ${@'${WAYLAND_PATCHES}' if '1' in '${USE_WAYLAND}' else ''}"
FILESEXTRAPATHS_prepend_rcar-gen2 := \
    "${@'${THISDIR}/${PN}/wayland:' if '1' in '${USE_WAYLAND}' else ''}"

# for x11
X11_PATCHES = " \
    file://0001-omx-renesas-rcar-support.patch \
    file://0002-h264dec-support-mp4-container.patch \
    file://0003-omx-change-reset-to-flush.patch \
    file://0004-omx-fix-flushing-issue.patch \
    file://0005-omx-fix-not-negotiated-issue.patch \
    file://0006-omx-workaround-for-timestamp-issue.patch \
    file://0007-support_audiodec_and_aacdec.patch \
    file://0008-omxvideodec-support-no-copy-mode.patch \
    file://0009-omxvideodec-fix_not_negotiated.patch \
    file://0010-support_nal_length_size_1_or_2.patch \
    file://0011-videodec-Manually-calculate-timestamp-if-not-provided.patch \
    file://0012-h264dec-Add-setting-for-StoreUnit-and-Deinterl.patch \
    file://0013-videodec-Support-no-reorder-option.patch \
    file://0014-videodec-Fix-slow-issue-with-filler-data.patch \
    file://0015-videodec-Update-for-no-copy.patch \
    file://0016-videodec-Workaround-for-Decoder-initialize-error.patch \
    file://0017-omxvideodec-release-frames-with-old-PTS-to-avoid-mem.patch \
    file://0018-videodec-Not-clean-older-frames-in-no_reorder.patch \
    file://0019-videodec-Remove-Bug-in-decoder-message.patch \
    file://0020-h264enc-add-omxh264enc.patch \
    file://0021-videodec-add-dmabuf-feature.patch \
    file://0022-omx-fix-two-serious-message-handling-bugs.patch \
	file://0023-videodec-fix-issue-with-wrong-timestamp-ES-stream.patch \
    file://0024-264dec-remove-not-supported-aligment-nal.patch \
    file://0025-videoenc-Align-stride-due-to-limitation-of-encode-MC.patch \
    file://0026-videodec-revise-dmabuf-implement.patch \
"

SRC_URI_append_rcar-gen2 = " \
    ${@'${X11_PATCHES}' if '${USE_X11}' == '1' and '${USE_WAYLAND}' == '0' else ''}"
FILESEXTRAPATHS_prepend_rcar-gen2 := \
    "${@'${THISDIR}/${PN}/x11:' if '${USE_X11}' == '1' and '${USE_WAYLAND}' == '0' else ''}"

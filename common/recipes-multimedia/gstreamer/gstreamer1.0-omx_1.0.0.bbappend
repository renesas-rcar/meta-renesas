FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
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

EXTRA_OECONF += "--enable-experimental"

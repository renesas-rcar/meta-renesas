include gstreamer1.0-omx.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"

SRC_URI[md5sum] = "bb34b5742223267298bcffc209104a92"
SRC_URI[sha256sum] = "7a1d8d28d70dacc6bd3c7ee7d7e40df6d5a1d38d7c256d5c9c5c8ef15c005014"

S = "${WORKDIR}/gst-omx-${PV}"

PRINC := "${@int(PRINC) + 1}"

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

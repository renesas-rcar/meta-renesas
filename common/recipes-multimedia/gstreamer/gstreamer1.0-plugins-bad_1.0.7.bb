include gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=71a904d99ce7ae0c1cf129891b98145c"

SRC_URI[md5sum] = "d1493d1219b836a8cbf54f4fba962420"
SRC_URI[sha256sum] = "5f49e6353fdc855834b5beb054b3a47ef5fa558006c7eda6d2ec07b36315c2ab"

S = "${WORKDIR}/gst-plugins-bad-${PV}"

DEPENDS += "directfb faad2 libxml2 libuiomux libshvio"

PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

TARGET_CFLAGS += "-D_GNU_SOURCE"
PACKAGECONFIG[directfb] = "--enable-directfb,--disable-directfb,directfb"

PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('curl', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('eglgles', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('wayland', '')}"
PACKAGECONFIG += "faad directfb"

EXTRA_OECONF += "--enable-directfb --enable-experimental --disable-nls"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-1.0.7.tar.xz \
	file://0001-h264parse-add-a-new-capability-no-sync-code.patch \
	file://0002-h264parse-set-GST_BUFFER_FLAG_HEADER-flag-if-buffer-.patch \
	file://0003-h264parse-don-t-repeatedly-invoke-finish_frame-for-a.patch \
	file://0004-h264parse-set-the-same-timestamp-as-a-last-frame-or-.patch \
	file://0005-dfbvideosink-change-interfaces-header-pathes.patch \
	file://0006-dfbvideosink-temporarily-remove-the-preallocated-buf.patch \
	file://0007-dfbvideosink-declare-the-object-type-definition-with.patch \
	file://0008-dfbvideosink-set-GstColorBalanceType-with-get_balanc.patch \
	file://0009-dfbvideosink-support-the-treatment-of-the-GstCaps-st.patch \
	file://0010-dfbvideosink-replace-GST_FLOW_UNEXPECTED-with-GST_FL.patch \
	file://0011-dfbvideosink-write-to-framebuffer-for-each-plane.patch \
	file://0012-dfbvideosink-remove-from-non-ported-list-in-configur.patch \
	file://0013-dfbvideosink-add-NV12-pixel-format-support.patch \
	file://0014-dfbvideosink-invoke-SetConfiguration-regardless-of-v.patch \
	file://0015-dfbvideosink-adjust-the-framebuffer-offset-and-strid.patch \
	file://0016-dfbvideosink-add-the-methods-to-manage-preallocated-.patch \
	file://0017-dfbvideosink-invoke-GstBufferPool-class-methods.patch \
	file://0018-dfbvideosink-handle-a-DirectFB-surface-to-use-blitti.patch \
	file://0019-dfbvideosink-handle-a-buffer-allocation-query-with-p.patch \
	file://0020-dfbvideosink-give-top-priority-to-NV12-color-format-.patch \
	file://0021-dfbvideosink-disable-signal-handler-owned-by-DirectF.patch \
	"

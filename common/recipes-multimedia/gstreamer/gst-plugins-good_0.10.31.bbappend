FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PACKAGECONFIG_armadillo800eva += "v4l"

SRC_URI_append_armadillo800eva = " \
    file://0001-sys-v4l2-gstv4l2-fix-the-depth-value-for-RGB32.patch \
    file://0002-Revert-sys-v4l2-gstv4l2-fix-the-depth-value-for-RGB3.patch \
    file://0003-sys-v4l2-gstv4l2-register-uiomux-memory-regions-via-.patch \
    file://0004-sys-v4l2-gstv4l2-release-corresponding-uiomux-handle.patch \
    file://0005-videocrop-correct-coding-style-with-gst-indent.patch \
    file://0006-videocrop-send-a-query-whether-the-rowstride-capabil.patch \
    file://0007-videocrop-set-rowstride-capability.patch \
    file://0008-videocrop-kick-off-the-zero-copy-cropping.patch \
    file://0009-avidemux-set-frame-start-code-to-VC-1-advanced-profi.patch \
    file://0010-videocrop-add-gst_video_crop_get_image_details_from_.patch \
    file://0011-videocrop-change-the-unit-of-rowstride-to-byte.patch \
    file://0012-videocrop-add-tests-to-determine-if-width-and-height.patch \
    file://0013-videocrop-replace-the-processing-to-set-gint-value-t.patch \
    file://0014-videocrop-don-t-set-rowstride-only-when-the-color-sp.patch \
    file://0015-videocrop-fix-wrong-subbuffer-size.patch \
    file://0016-videocrop-support-getting-NV12-image-details.patch \
    file://0017-videocrop-add-NV12-format-caps-template.patch \
    file://0018-videocrop-kick-off-NV12-zero-copy-cropping.patch \
    file://0019-videocrop-send-a-query-by-the-first-invorker-of-quer.patch \
    file://0020-videocrop-set-tile-boundary-offset-in-caps-for-T-L-a.patch \
    file://0021-videocrop-support-cropping-interlaced-images.patch \
    file://0022-videocrop-move-output-buffer-size-calculation-to-tra.patch \
    file://0023-videocrop-skip-the-transforming-caps-process-when-th.patch \
    file://0024-v4l2src-fix-RGB32-colorspace-deinitions.patch \
    file://0025-sys-v4l2-gstv4l2-skip-set-caps-processing-if-the-par.patch \
    file://0026-sys-v4l2-gstv4l2-return-an-error-from-set_caps-if-tr.patch \
    file://0027-videocrop-add-a-new-function-to-determine-if-source-.patch \
    file://0028-videocrop-set-result-of-determing-if-source-images-a.patch \
    file://0029-videocrop-round-down-cropping-parameters-when-the-co.patch \
    file://0030-videocrop-hold-a-lock-to-prevent-from-accessing-crop.patch \
    file://0031-videocrop-fix-a-memory-leak-caused-by-invoking-gst_p.patch \
    "
require gst-plugins-private-libs.inc

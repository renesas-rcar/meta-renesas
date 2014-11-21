require ../../include/gles-control.inc

PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

PACKAGECONFIG_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '', 'fbdev', d)}"
DEPENDS_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'gles-user-module', '', d)}"
EXTRA_OECONF_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '--enable-v4l2', \
        '--disable-xwayland-test WESTON_NATIVE_BACKEND=fbdev-backend.so', d)}"

SRC_URI_append_rcar-gen2 = "${@base_conditional('USE_GLES', '1', ' \
        file://0001-Add-V4L2-media-controller-support-library.patch \
        file://0002-Add-V4L2-media-controller-renderer.patch \
        file://0003-V4L2-renderer-workaround-for-a-bulid-error.patch \
        file://0004-Add-support-for-V4L2-renderer-in-DRM-compositor.patch \
        file://0005-Add-R-Car-VSP1-device-support-for-V4L2-renderer.patch \
        file://0006-v4l2-renderer-receive-a-mapped-address-and-a-stride-.patch \
        file://0007-v4l2-renderer-pass-v4l2_bo_state-to-set_output_buffe.patch \
        file://0008-v4l2-renderer-support-read_pixels.patch \
        file://0009-v4l2-renderer-add-support-for-alpha.patch \
        file://0010-vsp-renderer-device-add-support-for-alpha-channel.patch \
        file://0011-vsp-renderer-open-subdev-via-v4l2_subdev.patch \
        file://0012-vsp-renderer-add-a-support-for-scaling.patch \
        file://0013-v4l2-renderer-check-validity-of-DMABUF-before-compos.patch \
        file://0014-v4l2-renderer-Add-gl-fallback-feature.patch \
        file://0015-vsp-renderer-add-a-support-for-gl-fallback-feature.patch \
        file://0016-workaround-refer-to-the-global-gl_renderer-when-dest.patch \
        file://0017-Revert-workaround-refer-to-the-global-gl_renderer-wh.patch \
        file://0018-v4l2-renderer-destroy-surface-in-correct-order.patch \
        file://0019-v4l2-renderer-fix-rendering-posititon.patch \
        file://0020-vsp-renderer-cope-to-negative-offsets.patch \
        file://0021-vsp-renderer-fix-debug-messages.patch \
        file://0022-v4l2-renderer-skip-view-not-attached-to-the-renderer.patch \
       ', '', d)}"

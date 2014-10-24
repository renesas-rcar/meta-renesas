require ../../include/gles-control.inc

# for wayland
PACKAGECONFIG_remove_rcar-gen2 = "${@'orc' if '1' in '${USE_WAYLAND}' else ''}"
PACKAGECONFIG_append_rcar-gen2 = " faad ${@base_contains('USE_WAYLAND', '1', 'wayland', '', d)}"

FILESEXTRAPATHS_prepend_rcar-gen2 := \
    "${@'${THISDIR}/${PN}/wayland:' \
        if '1' in '${USE_GLES_WAYLAND}' else ''}"

WAYLAND_PATCHES = " \
     file://0001-waylandsink-Add-the-implementation-to-use-wayland-km.patch \
     file://0002-waylandsink-Move-the-display-structure-allocation-fr.patch \
     file://0003-waylandsink-Change-wayland-display-property-from-dis.patch \
     file://0004-waylandsink-Change-error-message-macros-from-GST_ERR.patch \
     file://0005-waylandsink-Don-t-set-wl_shell-surface-listener-when.patch \
     file://0006-waylandsink-Change-the-amount-of-buffers-to-be-alloc.patch \
     file://0007-Waylandsink-testing-add-a-fullscreen-mode.patch \
     file://0009-waylandsink-Correct-coding-style-by-gst-indent.patch \
     file://0010-waylandsink-Ensure-that-the-frame-sync-callback-has-.patch \
     file://0011-waylandsink-Use-a-private-event-queue-for-dispatchin.patch \
     file://0012-waylandsink-Support-double-buffering.patch \
     file://0013-waylandsink-Replace-wl_display_dispatch-with-wl_disp.patch \
     file://0014-waylandsink-Synchronize-returning-a-buffer-to-the-bu.patch \
     file://0015-waylandsink-Remove-an-unused-member-variable-of-the-.patch \
     file://0016-waylandsink-Fix-a-missing-dereferencing-of-the-buffe.patch \
     file://0017-waylandsink-Wait-for-the-frame-callback-for-the-last.patch \
     file://0018-waylandsink-Initialize-a-wl_callback-pointer-value-a.patch \
     file://0019-waylandsink-Fill-the-window-structure-with-zero-by-u.patch \
     file://0020-waylandsink-Check-if-the-wl_display-has-been-created.patch \
     file://0021-waylandsink-Fix-the-number-of-the-buffers-to-be-regi.patch \
     file://0022-waylandsink-Declare-the-definition-that-indicates-th.patch \
     file://0023-waylandsink-Preliminary-for-the-gstdmabuf-allocator-.patch \
     file://0024-waylandsink-Restrict-the-number-of-buffers-in-the-po.patch \
"

SRC_URI_append_rcar-gen2 = " \
    ${@'${WAYLAND_PATCHES}' if '1' in '${USE_GLES_WAYLAND}' else ''}"

# for x11
REPLACE_X11 = " \
    ${@'wayland' if 'x11' in '${DISTRO_FEATURES}' else ''}"
PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('${REPLACE_X11}', '')}"

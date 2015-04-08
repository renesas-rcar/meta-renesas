require ../../include/gles-control.inc
require ../../include/multimedia-control.inc

PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

PACKAGECONFIG_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '', 'fbdev', d)}"
DEPENDS_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'gles-user-module', '', d)}"
REPENDS_append_rcar-gen2 = " \
        ${@'vsp2-kernel-module' \
            if '${USE_GLES}' == '1' and '${USE_MULTIMEDIA}' == '1' else ''}"
EXTRA_OECONF_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '--enable-v4l2', \
        '--disable-xwayland-test WESTON_NATIVE_BACKEND=fbdev-backend.so', d)}"

SRCREV_rcar-gen2 = "${@'79c03392966237689f478d41461726e67a0dc228' \
	if '1' in '${USE_GLES}' else '00781bcf518f6bab0d08e6962630b0994e8bf632'}"
SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/weston.git;protocol=git;branch=RCAR-GEN2/1.5.0/gl-fallback \
	file://weston.desktop \
	file://weston.png \
	file://disable-wayland-scanner-pkg-check.patch \
	file://make-lcms-explicitly-configurable.patch \
	file://make-libwebp-explicitly-configurable.patch \
"
SRC_URI_append_rcar-gen2 = \
    "${@'file://vsp-renderer-Change-VSP-device-from-VSP1-to-VSP2.patch' \
        if '${USE_GLES}' == '1' and '${USE_MULTIMEDIA}' == '1' else ''}"
S = "${WORKDIR}/git"

RDEPENDS_${PN}_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'media-ctl', '', d)}"

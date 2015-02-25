require ../../include/gles-control.inc

PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

PACKAGECONFIG_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '', 'fbdev', d)}"
DEPENDS_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'gles-user-module', '', d)}"
EXTRA_OECONF_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '--enable-v4l2', \
        '--disable-xwayland-test WESTON_NATIVE_BACKEND=fbdev-backend.so', d)}"

SRCREV_rcar-gen2 = "${@'decdc587ceb147d22eb989a3334e627208030f69' \
	if '1' in '${USE_GLES}' else '00781bcf518f6bab0d08e6962630b0994e8bf632'}"
SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/weston.git;protocol=git;branch=RCAR-GEN2/1.5.0/gl-fallback \
	file://weston.desktop \
	file://weston.png \
	file://disable-wayland-scanner-pkg-check.patch \
	file://make-lcms-explicitly-configurable.patch \
	file://make-libwebp-explicitly-configurable.patch \
"
S = "${WORKDIR}/git"

RDEPENDS_${PN}_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'media-ctl', '', d)}"

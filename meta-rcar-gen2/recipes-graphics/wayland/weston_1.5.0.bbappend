require ../../include/gles-control.inc

PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

PACKAGECONFIG_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '', 'fbdev', d)}"
DEPENDS_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', 'gles-user-module', '', d)}"
EXTRA_OECONF_append_rcar-gen2 = " \
        ${@base_conditional('USE_GLES', '1', '--enable-v4l2', \
        '--disable-xwayland-test WESTON_NATIVE_BACKEND=fbdev-backend.so', d)}"

SRCREV_rcar-gen2 = "${@'d7f2d0e5f4430535a9f2c187fc656629dc70a804' \
	if '1' in '${USE_GLES}' else '00781bcf518f6bab0d08e6962630b0994e8bf632'}"
SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/weston.git;protocol=git;branch=RCAR-GEN2/1.5.0/gl-fallback \
	file://weston.desktop \
	file://weston.png \
	file://disable-wayland-scanner-pkg-check.patch \
	file://make-lcms-explicitly-configurable.patch \
	file://make-libwebp-explicitly-configurable.patch \
"
S = "${WORKDIR}/git"

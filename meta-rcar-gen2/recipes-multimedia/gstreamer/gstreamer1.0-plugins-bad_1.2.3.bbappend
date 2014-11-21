require ../../include/gles-control.inc

SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-bad.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "${@'d84669577cd201071d73a097572fd59d9a8cdd78' \
	if '1' in '${USE_GLES_WAYLAND}' else 'c1f5e09ce341f3438fb601a852ee70e72d375646'}"
S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

# for wayland
PACKAGECONFIG_remove_rcar-gen2 = "${@'orc' if '1' in '${USE_GLES_WAYLAND}' else ''}"
PACKAGECONFIG_append_rcar-gen2 = " faad ${@base_contains('USE_GLES_WAYLAND', '1', 'wayland', '', d)}"

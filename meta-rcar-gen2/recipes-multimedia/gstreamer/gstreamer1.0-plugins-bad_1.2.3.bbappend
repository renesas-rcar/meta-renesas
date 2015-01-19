require ../../include/gles-control.inc

SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-bad.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "${@'07015d9ba6f53d4e08a3623a6d8d9466faff139c' \
	if '1' in '${USE_GLES_WAYLAND}' else 'c1f5e09ce341f3438fb601a852ee70e72d375646'}"
LIC_FILES_CHKSUM_remove_rcar-gen2 = "\
	file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607"

S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

# for wayland
PACKAGECONFIG_remove_rcar-gen2 = "${@'orc' if '1' in '${USE_GLES_WAYLAND}' else ''}"
PACKAGECONFIG_append_rcar-gen2 = " faad ${@base_contains('USE_GLES_WAYLAND', '1', 'wayland', '', d)}"

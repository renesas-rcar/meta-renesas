SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-ugly.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "258bf9862e5b7179562f0cae9fb470f6486dca3a"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= " \
    a52dec lame mad mpeg2dec \
    "

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

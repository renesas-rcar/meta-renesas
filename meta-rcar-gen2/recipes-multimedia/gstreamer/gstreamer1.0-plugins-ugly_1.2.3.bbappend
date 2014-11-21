SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-ugly.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "af884db954b53bf083ebc39e3a90b639f81513e1"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= " \
    a52dec lame mad mpeg2dec \
    "

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

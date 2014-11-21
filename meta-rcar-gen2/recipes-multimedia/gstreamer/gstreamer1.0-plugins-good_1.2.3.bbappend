SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-good.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "152ad1a6c86db5ec405ac95279ea37b63b43cd9f"
S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

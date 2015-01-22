SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-good.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "e752441fef9f70c97bb0976476fdaa6c7df27b6b"

LIC_FILES_CHKSUM_remove_rcar-gen2 = "\
	file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607"

S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

DESCRIPTION = "A library for controlling the SH-Mobile VIO/VEU"
AUTHOR = "Phil Edworthy <phil.edworthy@renesas.com>"
BUGTRACKER = ""

SECTION = "libs"
PRIORITY = "optional"

PR = "r0"

inherit autotools pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=838c366f69b72c5df05c96dff79b35f2"

SRCREV = "3cebf3b073c2f22c60109e345670641a77c5981f"
SRC_URI = "git://github.com/renesas-devel/libshvio.git"
S = "${WORKDIR}/git/"

DEPENDS = "libuiomux libshmeram"
EXTRA_OECONF = "--enable-meram"

do_configure() {
	autoreconf -ivf ${S}
	oe_runconf
}

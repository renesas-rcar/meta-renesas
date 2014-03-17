DESCRIPTION = "library for accessing the SH-Mobile MERAM"
AUTHOR = "Damian Hobson-Garcia <dhobsong@igel.co.jp>"
BUGTRACKER = ""

SECTION = "libs"
PRIORITY = "optional"

PR = "r1"

inherit autotools pkgconfig

LICENSE = "LGPLv2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=3214f080875748938ba060314b4f727d"

SRCREV = "c7d54646cddfc6759ef64a6c22a3ea9e2c61888c"
SRCREV_armadillo800eva = "890bbd50e3a305a2331a4e2016ead968bdf916bb"
SRC_URI = "git://github.com/renesas-devel/libshmeram.git \
	file://configfile-path.patch \
	"
S = "${WORKDIR}/git/"

DEPENDS = "libuiomux"

do_configure() {
	autoreconf -ivf ${S}
	oe_runconf
}

# install to libshmeram
FILES_${PN} += "${sysconfdir}/*"

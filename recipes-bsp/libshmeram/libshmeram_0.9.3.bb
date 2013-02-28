DESCRIPTION = "library for accessing the SH-Mobile MERAM"
AUTHOR = "Damian Hobson-Garcia <dhobsong@igel.co.jp>"
BUGTRACKER = ""

SECTION = "libs"
PRIORITY = "optional"

PR = "r0"

inherit autotools pkgconfig

LICENSE = "LGPLv2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=3214f080875748938ba060314b4f727d"

SRCREV = "d276e8ff80386c2cc8b02619dd2511db97d88910"
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

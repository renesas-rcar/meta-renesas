DESCRIPTION = "UIOMux is a conflict manager for system resources, including UIO devices."
AUTHOR = "Conrad Parker <conrad@metadecks.org>"
HOMEPAGE = "https://github.com/renesas-devel/libuiomux"
BUGTRACKER = ""

SECTION = "libs"
PRIORITY = "optional"

PR = "r0"

inherit autotools pkgconfig

LICENSE = "LGPLv2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=3214f080875748938ba060314b4f727d"

SRCREV = "f0197adfbf2067d886b2bceeeffd4dba396f8686"
SRC_URI = "git://github.com/renesas-devel/libuiomux.git;protocol=git"
S = "${WORKDIR}/git/"

EXTRA_OECONF = "--with-max-map-mem=105"

do_configure() {
	autoreconf -ivf ${S}
	oe_runconf
}

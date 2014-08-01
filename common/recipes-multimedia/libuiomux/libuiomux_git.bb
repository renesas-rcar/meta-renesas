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
SRCREV_armadillo800eva = "2f40baf6c351ab49c852a43b331b080c5f4549ae"
SRC_URI = "git://github.com/renesas-devel/libuiomux.git;protocol=git"
S = "${WORKDIR}/git/"

UIO_MAX_MAP_MEM_armadillo800eva = "128"
EXTRA_OECONF_bockw = "--with-max-map-mem=${UIO_MAX_MAP_MEM}"
EXTRA_OECONF_armadillo800eva = "--with-max-map-mem=${UIO_MAX_MAP_MEM}"

do_configure() {
	autoreconf -ivf ${S}
	oe_runconf
}

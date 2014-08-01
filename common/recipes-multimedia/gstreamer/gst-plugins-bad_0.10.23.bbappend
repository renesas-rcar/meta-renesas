DEPENDS += "gstreamer libxml2"
EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"
EXTRA_OECONF += "--with-plugins=h264parse,asfmux,videoparsers"

TARGET_CFLAGS += "-D_GNU_SOURCE"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# For armadillo
SRCREV_armadillo800eva = "6c0a11cb57d4425f6d721a6756c5af4d9dd269e5"
SRC_URI_armadillo800eva = "git://github.com/matsu/gst-plugins-bad.git \
	file://0001-Setup-MERAM-for-A1.patch"
S_armadillo800eva = "${WORKDIR}/git/"
DEPENDS_append_armadillo800eva = " directfb libuiomux libshvio"
EXTRA_OECONF_armadillo800eva := "${@'${EXTRA_OECONF}'.replace('--disable-directfb', '--enable-directfb')}"

EXTRA_OECONF_append_armadillo800eva = " \
	--disable-librfb --enable-introspection=no \
	--disable-nls --disable-static --disable-gsettings \
"

do_configure_armadillo800eva() {
	(cd ${S}; sh autogen.sh --noconfigure)
	oe_runconf
}

FILES_${PN} += "${bindir}"
require gst-plugins-private-libs.inc

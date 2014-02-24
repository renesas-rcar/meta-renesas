DESCRIPTION = "GStreamer plug-in that allows communication with OpenMAX IL components"
AUTHOR = "Katsuya Matsubara <matsu@igel.co.jp>"
BUGTRACKER = ""

DEFAULT_PREFERENCE = "-1"
DEPENDS = "gstreamer"
RDEPENDS_${PN} = "libomxil"
LICENSE = "LGPLv2.1"
LICENSE_FLAGS = "commercial"                                                                                                                                                                  

require gst-plugins.inc
inherit gettext

LIBV="0.10"
PR = "r1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
EXTRA_OECONF += "--disable-valgrind"

# for armadillo800eva
SRCREV_armadillo800eva = "43e0be40d82f83308d0a17cd74060b280c30c2a8"
SRC_URI_armadillo800eva = "git://github.com/matsu/gst-openmax.git \
	file://gst-openmax.conf \
"

SRCREV = "3dad1ec6803bb2f2627188ce0e957dbeaa57b1be"
SRC_URI = "git://github.com/renesas-devel/gst-openmax.git \
	file://0001-base_videodec-change-the-tile-height-for-T-L-address.patch \
	file://0001-Stop-using-deprecated-GLib-thread-API.patch \
	file://disable_configure.patch \
"

S = "${WORKDIR}/git/"

# for -Werror-deprecated-declarations
CPPFLAGS += "-Wno-deprecated-declarations"
EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"

do_configure_prepend() {
	(cd ${S}; sh autogen.sh --noconfigure)
}

do_install_append_armadillo800eva() {
	install -d ${D}/home/root/.config/
	install -m 644 ${WORKDIR}/gst-openmax.conf ${D}/home/root/.config/.
}

FILES_${PN} += "${libdir}/gstreamer-${LIBV}/libgstomx.so /home/root/.config"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/libgstomx.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

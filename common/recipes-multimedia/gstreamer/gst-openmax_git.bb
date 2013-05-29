DESCRIPTION = "GStreamer plug-in that allows communication with OpenMAX IL components"
AUTHOR = "Katsuya Matsubara <matsu@igel.co.jp>"
BUGTRACKER = ""

inherit gettext
require gst-plugins.inc
DEPENDS += "gst-plugins-base"

PR = "r0"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRCREV = "3dad1ec6803bb2f2627188ce0e957dbeaa57b1be"
SRC_URI = "git://github.com/renesas-devel/gst-openmax.git \
        file://0001-base_videodec-change-the-tile-height-for-T-L-address.patch \
        file://0001-Stop-using-deprecated-GLib-thread-API.patch \
        file://disable_configure.patch \
        "

S = "${WORKDIR}/git/"

EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"

do_configure_prepend() {
	( cd ${S}; sh autogen.sh )
}

# FILES_${PN} += "${sysconfdir}/*"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

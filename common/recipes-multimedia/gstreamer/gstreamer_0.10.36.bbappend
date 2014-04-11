FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer:"

SRC_URI_append_bockw = " \
	file://printf.patch \
"

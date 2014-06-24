FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_bockw += " \
    file://0001-asfdemux-asfpacket-set-frame-start-code-to-VC-1-adva.patch \
"
require gst-plugins-private-libs.inc

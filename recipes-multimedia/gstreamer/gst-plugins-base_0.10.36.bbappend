#DEPENDS += "v4l-utils"
PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-gstaudiosink-wait-for-gst_ring_buffer_advance-when-p.patch"

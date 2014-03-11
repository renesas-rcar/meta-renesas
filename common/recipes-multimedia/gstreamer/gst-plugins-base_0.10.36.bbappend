PRINC := "${@int(PRINC) + 2}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#DEPENDS_armadillo800eva := "${@'${DEPENDS}'.replace('libogg', '')}"
#DEPENDS_armadillo800eva := "${@'${DEPENDS}'.replace('libvorbis', '')}"

#EXTRA_OECONF_armadillo800eva += "--disable-example --disable-vorbis \
#	--disable-ivorbis --disable-ogg --disable-gio"     

SRC_URI += "file://0001-gstaudiosink-wait-for-gst_ring_buffer_advance-when-p.patch"

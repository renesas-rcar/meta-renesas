PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('cairo', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('flac', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('gdk-pixbuf', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('jpeg', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('libpng', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('soup', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('speex', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('taglib', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('pulseaudio', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

EXTRA_OECONF += "--disable-nls"

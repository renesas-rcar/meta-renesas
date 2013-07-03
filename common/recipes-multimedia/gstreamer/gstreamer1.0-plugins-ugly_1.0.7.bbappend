PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('a52dec', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('lame', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('mpeg2dec', '')}"

EXTRA_OECONF += "--disable-nls"

SRC_URI += " \
	file://0001-asfdemux-asfpacket-specify-the-offset-by-an-amount-o.patch \
	file://0002-asfdemux-asfpacket-set-frame-start-code-to-VC-1-adva.patch \
	"

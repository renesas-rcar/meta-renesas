PRINC := "${@int(PRINC) + 1}"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://Xsession"

do_install_prepend () {
	cp ${WORKDIR}/Xsession ${S}/etc/X11/.
}

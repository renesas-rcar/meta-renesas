PRINC := "${@int(PRINC) + 1}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://session-renesas"

do_install_prepend () {
	# backup original and overwrite
	if ! cmp -s ${WORKDIR}/session-renesas ${WORKDIR}/session ; then
		cp ${WORKDIR}/session ${WORKDIR}/session.orig
		cp ${WORKDIR}/session-renesas ${WORKDIR}/session
	fi
}

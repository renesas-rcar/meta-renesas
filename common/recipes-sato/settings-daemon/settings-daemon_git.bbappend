PRINC := "${@int(PRINC) + 1}"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://70settings-daemon.sh-renesas"

do_install_prepend () {
        # backup original and copy
        if ! cmp -s ${WORKDIR}/70settings-daemon.sh-renesas ${WORKDIR}/70settings-daemon.sh ; then
		cp ${WORKDIR}/70settings-daemon.sh ${WORKDIR}/70settings-daemon.sh.orig
		cp ${WORKDIR}/70settings-daemon.sh-renesas ${WORKDIR}/70settings-daemon.sh
        fi
}

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen2 = " file://weston.ini"

do_install_append_rcar-gen2() {
    install -D -m 644 ${WORKDIR}/weston.ini ${D}/etc/xdg/weston/weston.ini
}

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " \
    file://ssh.service \
"

do_install_append() {
    install -m 644 ${WORKDIR}/ssh.service ${D}${sysconfdir}/avahi/services
}

FILES_avahi-daemon_append = " \
    ${sysconfdir}/avahi/services/ssh.service \
"


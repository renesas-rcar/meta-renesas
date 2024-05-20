FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " \
    file://ssh.service \
"

do_install:append() {
    install -m 644 ${WORKDIR}/ssh.service ${D}${sysconfdir}/avahi/services
}

FILES:avahi-daemon:append = " \
    ${sysconfdir}/avahi/services/ssh.service \
"


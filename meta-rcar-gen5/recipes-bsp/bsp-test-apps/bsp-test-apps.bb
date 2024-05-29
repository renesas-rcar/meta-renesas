SUMMARY = "USB and RWDT test applications"
LICENSE = "CLOSED"

S = "${WORKDIR}/bsp-test-apps"

SRC_URI = " \
    file://bsp-test-apps.tar.gz \
"

do_install() {
    install -d ${D}${ROOT_HOME}
    install -m 755 ${S}/watchdog-test ${D}${ROOT_HOME}
    install -m 755 ${S}/gadgetfs ${D}${ROOT_HOME}
    install -m 755 ${S}/wcrc_sample ${D}${ROOT_HOME}
}

FILES_${PN} = "${ROOT_HOME}/*"


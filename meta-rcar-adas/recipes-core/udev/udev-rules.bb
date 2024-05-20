SUMMARY = "udev rules for R-Car Gen3 CV Library"
LICENSE = "CLOSED"

SRC_URI = " \
    file://51-uio.rules \
    file://52-cmem.rules \
"

do_install () {
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/51-uio.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/52-cmem.rules ${D}${sysconfdir}/udev/rules.d/
}

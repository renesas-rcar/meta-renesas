SUMMARY = "udev rules for R-Car UIO drivers"
LICENSE = "CLOSED"

SRC_URI = " \
    file://51-uio.rules \
    file://52-cmem.rules \
"

S = "${WORKDIR}/${BPN}-${PV}"
UNPACKDIR = "${S}"

do_install () {
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${S}/51-uio.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${S}/52-cmem.rules ${D}${sysconfdir}/udev/rules.d/
}

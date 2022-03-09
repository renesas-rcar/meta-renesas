SUMMARY = "udev rules for R-Car Gen4 CPU HotPlug"
LICENSE = "CLOSED"

SRC_URI = " \
    file://99-cpuhotplug-udev.rules \
"

do_install () {
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/99-cpuhotplug-udev.rules ${D}${sysconfdir}/udev/rules.d/
}

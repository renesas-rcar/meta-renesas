SUMMARY = "BSP configuration (default/ADAS) script"
LICENSE = "CLOSED"

S = "${WORKDIR}"

SRC_URI = " \
    file://bsp-config_v4h.sh \
"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/*.sh ${D}${bindir}/
}

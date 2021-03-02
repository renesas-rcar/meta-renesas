SUMMARY = "BSP configuration (default/ADAS) script"
LICENSE = "CLOSED"

S = "${WORKDIR}"

SRC_URI = " \
    file://bsp-config_v3m.sh \
    file://bsp-config_v3h.sh \
    file://bsp-config_v3u.sh \
"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/*.sh ${D}${bindir}/
}

DESCRIPTION = "QOS Interface test app for R-Car Gen3"

require qosif.inc

DEPENDS = "qosif-user-module"
PN = "qosif-tp-user-module"
PR = "r0"

S = "${WORKDIR}/git"
QOSIF_TP_DIR = "qos_if-tp-user/files/qos_if"

do_compile() {
    cd ${S}/${QOSIF_TP_DIR}
    oe_runmake
}

do_install() {
    # Create destination directory
    install -d ${D}/usr/local/bin/

    # Copy user test program
    install -m 755 ${S}/${QOSIF_TP_DIR}/qos_tp ${D}/usr/local/bin/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dbg \
"

FILES_${PN} = "/usr/local/bin/qos_tp"

FILES_${PN}-dbg = " \
    /usr/local/bin/.debug/* \
"

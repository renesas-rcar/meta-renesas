DESCRIPTION = "VSP Manager Interface test app for R-Car Gen3"

require vspmif.inc

DEPENDS = "vspmif-user-module mmngr-user-module"
PN = "vspmif-tp-user-module"
PR = "r0"

S = "${WORKDIR}/git"
VSPMIF_TP_DIR = "vspm_if-tp-user/files/vspm_if"

# Get Wordsize of test app and change their names later to avoid override
WS_aarch64 = ""
WS_virtclass-multilib-lib32 = "32"

do_compile() {
    cd ${S}/${VSPMIF_TP_DIR}
    make all
}

do_install() {
    # Create destination folder
    install -d ${D}${RENESAS_DATADIR}/bin/

    # Copy user test program
    if [ X${WS} = "X32" ]; then
        install -m 755 ${S}/${VSPMIF_TP_DIR}/vspm_tp ${D}${RENESAS_DATADIR}/bin/vspm_tp32
        install -m 755 ${S}/${VSPMIF_TP_DIR}/fdpm_tp ${D}${RENESAS_DATADIR}/bin/fdpm_tp32
    else
        install -m 755 ${S}/${VSPMIF_TP_DIR}/vspm_tp ${D}${RENESAS_DATADIR}/bin/
        install -m 755 ${S}/${VSPMIF_TP_DIR}/fdpm_tp ${D}${RENESAS_DATADIR}/bin/
    fi
}

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
"
FILES_${PN} = " \
    ${@base_conditional('WS', '32', '${RENESAS_DATADIR}/bin/vspm_tp32 ${RENESAS_DATADIR}/bin/fdpm_tp32', \
    '${RENESAS_DATADIR}/bin/vspm_tp ${RENESAS_DATADIR}/bin/fdpm_tp', d)}"

FILES_${PN}-dbg = " \
    ${RENESAS_DATADIR}/bin/.debug/*"

RPROVIDES_${PN} += "vspmif-tp-user-module"

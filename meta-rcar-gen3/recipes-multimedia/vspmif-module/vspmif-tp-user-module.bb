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
    install -d ${D}/usr/local/bin/

    # Copy user test program
    if [ X${WS} = "X32" ]; then
        install -m 755 ${S}/${VSPMIF_TP_DIR}/vspm_tp ${D}/usr/local/bin/vspm_tp32
        install -m 755 ${S}/${VSPMIF_TP_DIR}/fdpm_tp ${D}/usr/local/bin/fdpm_tp32
    else
        install -m 755 ${S}/${VSPMIF_TP_DIR}/vspm_tp ${D}/usr/local/bin/
        install -m 755 ${S}/${VSPMIF_TP_DIR}/fdpm_tp ${D}/usr/local/bin/
    fi
}

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
"
FILES_${PN} = " \
    ${@base_conditional('WS', '32', '/usr/local/bin/vspm_tp32 /usr/local/bin/fdpm_tp32', \
    '/usr/local/bin/vspm_tp /usr/local/bin/fdpm_tp', d)}"

FILES_${PN}-dbg = " \
    /usr/local/bin/.debug/*"

RPROVIDES_${PN} += "vspmif-tp-user-module"

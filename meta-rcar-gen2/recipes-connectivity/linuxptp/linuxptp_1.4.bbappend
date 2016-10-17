# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-sk-fix-error-check-when-hwts_init.patch \
    file://0002-sk-replaced-the-priority-of-hwtstamp-filter1-and-fil.patch \
"

do_install_append() {
    install -d ${D}/${sysconfdir}/linuxptp/
    install -m 644 default.cfg gPTP.cfg ${D}/${sysconfdir}/linuxptp
}

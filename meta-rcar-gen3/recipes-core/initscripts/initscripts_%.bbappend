FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

S = "${WORKDIR}"

SRC_URI_append = " \
    file://adas-switch-init \
"

do_install_append() {
    install -d ${D}/${sysconfdir}/init.d
    if [ "${@base_contains('MACHINE_FEATURES', 'h3ulcb-had', 'h3ulcb-had', '', d)}" = "h3ulcb-had" ]; then
        install -m 755 ${S}/adas-switch-init ${D}/${sysconfdir}/init.d/adas-switch-init
        update-rc.d -r ${D} adas-switch-init start 5 S .
    fi
}

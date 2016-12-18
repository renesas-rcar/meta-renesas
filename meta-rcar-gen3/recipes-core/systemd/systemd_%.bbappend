inherit groupadd_useradd_prepend

EXTRA_OECONF += " \
    --disable-timesyncd \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SS = "${WORKDIR}"

SRC_URI_append = " \
    file://adas-switch-init.service \
"

do_install_append() {
    if [ "${@base_contains('MACHINE_FEATURES', 'h3ulcb-had', 'h3ulcb-had', '', d)}" = "h3ulcb-had" ]; then
        if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
            install -d ${D}${systemd_unitdir}/system
            install -m 0644 ${SS}/adas-switch-init.service ${D}${systemd_unitdir}/system/
            install -d ${D}${systemd_unitdir}/system/sysinit.target.wants/
            ln -sf ../adas-switch-init.service ${D}${systemd_unitdir}/system/sysinit.target.wants/adas-switch-init.service
        fi
    fi
}

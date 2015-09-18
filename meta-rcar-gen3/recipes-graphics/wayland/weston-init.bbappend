require ../../include/gles-control.inc
inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_rcar-gen3 = " file://weston.service"

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/weston.service ${D}${systemd_unitdir}/system/
    fi

    if [ "X${USE_GLES}" = "X0" ]; then
        sed -e '/\[Service\]/a ExecStartPre=/usr/bin/chvt 7' \
            -e 's/-u root/-u root --tty \/dev\/tty7/g' \
            -e '/Wants=rc.pvr.service/d' \
            -e 's/rc.pvr.service//g' \
            -i ${D}${systemd_unitdir}/system/weston.service
    fi
}

SYSTEMD_SERVICE_${PN} = "weston.service"

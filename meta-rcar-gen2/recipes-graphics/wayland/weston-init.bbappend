require include/gles-control.inc
inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = " file://weston-env.sh"
SRC_URI_append_rcar-gen2 = " file://weston.service"

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/weston.service ${D}${systemd_unitdir}/system/
    fi

    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "/RequiresMountsFor=\/run/a Wants=rc.pvr.service" \
            -e "/RequiresMountsFor=\/run/a After=rc.pvr.service" \
            -e "s/\$OPTARGS/--idle-time=0 \$OPTARGS/" \
            -i ${D}${systemd_unitdir}/system/weston.service
    fi

    if [ "X${USE_MULTIMEDIA}" = "X1" ]; then
        if [ "X${USE_V4L2_RENDERER}" = "X1" ]; then
            sed -e "s/\$OPTARGS/--use-v4l2 \$OPTARGS/" \
                -i ${D}${systemd_unitdir}/system/weston.service
        fi
    fi

    install -d ${D}/etc/profile.d
    install -m 0755 ${WORKDIR}/weston-env.sh ${D}/etc/profile.d
}

FILES_${PN} += " /etc/profile.d/weston-env.sh"

SYSTEMD_SERVICE_${PN} = "weston.service"

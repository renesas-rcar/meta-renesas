require include/gles-control.inc
require include/omx-options.inc
inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_rcar-gen3 = " file://weston.service"

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
}

SYSTEMD_SERVICE_${PN} = "weston.service"

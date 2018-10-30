require include/gles-control.inc

do_install_append() {
    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "/RequiresMountsFor=\/run/a After=dbus.service multi-user.target" \
            -i ${D}/${systemd_system_unitdir}/weston.service
    fi
}

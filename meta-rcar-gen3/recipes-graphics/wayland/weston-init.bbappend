require include/gles-control.inc

do_install_append() {
    # Fix to run login before start weston.service
    sed -e "/User=root/a PAMName=login" \
        -i ${D}/${systemd_system_unitdir}/weston.service
    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "/RequiresMountsFor=\/run/a After=dbus.service multi-user.target" \
            -e "s/\$OPTARGS/--idle-time=0 \$OPTARGS/" \
            -i ${D}/${systemd_system_unitdir}/weston.service
    fi
}

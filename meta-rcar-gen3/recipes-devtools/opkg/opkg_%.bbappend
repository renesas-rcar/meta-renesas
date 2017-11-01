# Do not reload daemon configuration
do_install_append () {
    sed -e 's/\disable/--no-reload \disable/' \
        -i ${D}${systemd_unitdir}/system/opkg-configure.service
}

PACKAGECONFIG_remove = "timesyncd"

# Set journal RuntimeMaxSize to 64M as default to fix timeout
# dev-ttySN.device's when enable debug and use journal
# as systemd.log_target
do_install_append () {
    sed -e 's/.*RuntimeMaxUse.*/RuntimeMaxUse=64M/' \
        -i ${D}${sysconfdir}/systemd/journald.conf
}

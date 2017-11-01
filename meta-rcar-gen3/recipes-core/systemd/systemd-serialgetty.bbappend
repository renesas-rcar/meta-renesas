# W/A to fix serial-getty@.service timeout
do_install_append () {
    sed -e "/Before=getty.target/a Before=getty@tty1.service" \
        -e "/.*RestartSec.*/d" \
        -i ${D}${systemd_unitdir}/system/serial-getty@.service
}

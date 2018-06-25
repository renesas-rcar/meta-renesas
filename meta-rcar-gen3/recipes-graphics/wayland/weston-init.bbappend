require include/gles-control.inc

SRC_URI += " \
    file://weston.service \
"

do_install_append() {
    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "s/\$OPTARGS/--idle-time=0 \$OPTARGS/" \
            -i ${D}/${systemd_system_unitdir}/weston.service
    fi
}

require include/gles-control.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen3 = " \
    file://weston.ini \
    file://weston.sh \
"

do_install_append_rcar-gen3() {
    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "/^After=/s/$/ dbus.service multi-user.target/" \
            -e "s/\$OPTARGS/--idle-time=0 \$OPTARGS/" \
            -i ${D}/${systemd_system_unitdir}/weston@.service
    fi

    install -d ${D}/${sysconfdir}/xdg/weston
    # install weston.ini as sample settings of gl-renderer
    install -m 644 ${WORKDIR}/weston.ini ${D}/${sysconfdir}/xdg/weston/

    # Checking for ivi-shell configuration
    # If ivi-shell is enable, we will add its configs to weston.ini
    if [ "X${USE_WAYLAND_IVI_SHELL}" = "X1" ]; then
        sed -i '/repaint-window=34/c\repaint-window=34\nshell=ivi-shell.so' \
            ${D}/${sysconfdir}/xdg/weston/weston.ini
        sed -e '$a\\' \
            -e '$a\[ivi-shell]' \
            -e '$a\ivi-module=ivi-controller.so' \
            -e '$a\ivi-input-module=ivi-input-controller.so' \
            -e '$a\transition-duration=300' \
            -e '$a\cursor-theme=default' \
            -i ${D}/${sysconfdir}/xdg/weston/weston.ini
    fi

    # Set XDG_RUNTIME_DIR to /run/user/$UID (e.g. run/user/0)
    install -d ${D}/${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/weston.sh ${D}/${sysconfdir}/profile.d/weston.sh
}

FILES_${PN}_append_rcar-gen3 = " \
    ${sysconfdir}/profile.d/weston.sh \
"


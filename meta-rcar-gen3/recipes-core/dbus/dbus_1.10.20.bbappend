# WORKAROUND for memory leak issue when restart weston

# Make sure the WORKAROUND only effect with GFX and MMP config
ENABLE_FIX = "${@'1' if 'gsx' in '${MACHINE_FEATURES}' else '0'}"

# Modify dbus.service to make dbus restarts depend on weston
do_install_prepend() {
    if [ "X${ENABLE_FIX}" = "X1" ]; then
        sed -e "/Requires=dbus.socket/a PartOf=weston.service" \
        -i ${B}/bus/dbus.service
    fi
}

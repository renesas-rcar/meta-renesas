# These below libraries are provided by gles-user-module
PACKAGECONFIG_remove_rcar-v4x = "${@bb.utils.contains('MACHINE_FEATURES', 'gsx', 'egl gles', '', d)}"

do_install_append_rcar-v4x() {
    # Have to remove khrplatform.h file due to conflict with gles-user-module
    # even though libegl from mesa is removed
    if [ "${MACHINE_FEATURES}" =~ "gsx" ]; then
        rm -rf ${D}${includedir}/KHR
    fi
}


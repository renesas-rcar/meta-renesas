require include/gles-control.inc

PACKAGECONFIG_remove = "timesyncd"

# Avoid pushing opened device fds into PID1 by logind when restarting
do_install_append () {
    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e 's/FileDescriptorStoreMax=512/FileDescriptorStoreMax=0/' \
            -i ${D}/lib/systemd/system/systemd-logind.service
    fi
}

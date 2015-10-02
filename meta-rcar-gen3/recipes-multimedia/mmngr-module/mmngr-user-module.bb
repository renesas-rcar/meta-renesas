DESCRIPTION = "Memory Manager User module for Renesas R-Car Gen3"
require mmngr_lib.inc

DEPENDS = "kernel-module-mmngr"
PN = "mmngr-user-module"
PR = "r0"

S = "${WORKDIR}/git/libmmngr/mmngr"

EXTRA_OECONF =  "${@bb.utils.contains("DISTRO_FEATURES", "mm-test", \
    " --enable-mmngr-test", "", d)}"

exec_prefix = "/usr"
bindir = "/usr/local/bin"
includedir = "/usr/local/include"

do_install_append() {
    if [ -f ${D}/usr/local/bin/mmtp ]; then
        if [ X${WS} = "X32" ]; then
            mv ${D}/usr/local/bin/mmtp ${D}/usr/local/bin/mmtp${WS}
        fi
    fi
}

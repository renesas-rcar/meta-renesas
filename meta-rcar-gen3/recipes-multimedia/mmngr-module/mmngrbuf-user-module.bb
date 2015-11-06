DESCRIPTION = "Memory Manager Buffer User module for Renesas R-Car Gen3"
require mmngr_lib.inc

DEPENDS = "kernel-module-mmngrbuf"
PN = "mmngrbuf-user-module"
PR = "r0"

S = "${WORKDIR}/git/libmmngr/mmngrbuf"

EXTRA_OECONF =  "${@bb.utils.contains("DISTRO_FEATURES", "mm-test", \
    " --enable-mmngrbuf-test", "", d)}"

exec_prefix = "/usr"
bindir = "/usr/local/bin"
includedir = "/usr/local/include"

do_install_append() {
    if [ -f ${D}/usr/local/bin/mmbuftp ]; then
        if [ X${WS} = "X32" ]; then
            mv ${D}/usr/local/bin/mmbuftp ${D}/usr/local/bin/mmbuftp${WS}
        fi
    fi
}

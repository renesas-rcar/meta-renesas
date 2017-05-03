DESCRIPTION = "Memory Manager Buffer User module for Renesas R-Car Gen3"
require mmngr_lib.inc
require include/rcar-gen3-path-common.inc

DEPENDS = "kernel-module-mmngrbuf"
PN = "mmngrbuf-user-module"
PR = "r0"

S = "${WORKDIR}/git/libmmngr/mmngrbuf"

EXTRA_OECONF =  "${@bb.utils.contains("DISTRO_FEATURES", "mm-test", \
    " --enable-mmngrbuf-test", "", d)}"

exec_prefix = "/usr"
bindir = "${RENESAS_DATADIR}/bin"
includedir = "${RENESAS_DATADIR}/include"
CFLAGS += " -I${STAGING_DIR_HOST}${RENESAS_DATADIR}/include"

do_install_append() {
    if [ -f ${D}${RENESAS_DATADIR}/bin/mmbuftp ]; then
        if [ X${WS} = "X32" ]; then
            mv ${D}${RENESAS_DATADIR}/bin/mmbuftp ${D}${RENESAS_DATADIR}/bin/mmbuftp${WS}
        fi
    fi
}

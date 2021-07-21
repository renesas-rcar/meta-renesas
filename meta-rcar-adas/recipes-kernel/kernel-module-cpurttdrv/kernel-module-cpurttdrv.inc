inherit module

SRC_URI = "${RENESAS_CPURTTDRV_URL};nobranch=1"

S = "${WORKDIR}/git"

do_install_append () {
    install -d ${D}${prefix}/local/include
    install -m 644 ${S}/cpurtt_common.h ${D}${prefix}/local/include
}

FILES_${PN} = "${prefix}/local/include/cpurtt_common.h"

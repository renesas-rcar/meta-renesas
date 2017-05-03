DESCRIPTION = "Linux ICCOM library for Renesas R-Car Gen3"

require iccom-user-module.inc
require include/rcar-gen3-path-common.inc

DEPENDS = " \
    kernel-module-iccom-mfis \
"

PN = "iccom-user-module"
PR = "r0"

# log output level : INFO=0 LOW=1 MED=2 HIGH=3 ERROR=4 FATAL=5 NONE=255
# (the setting of 4 means that ERROR and FATAL log are output)
export MEG_LEV="4"

S = "${WORKDIR}/libiccom"
B = "${S}/source"

do_install() {
    # Create destination directories
    install -d ${D}/${libdir}
    install -d ${D}${RENESAS_DATADIR}/include

    # Install library
    install -m 755 ${B}/libiccom.so ${D}/${libdir}/

    # Install shared header file
    install -m 644 ${S}/include/iccom_if_app.h ${D}${RENESAS_DATADIR}/include/
}

FILES_${PN} = " \
    ${libdir}/libiccom.so \
"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/include/iccom_if_app.h \
"

# Skip debug split
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

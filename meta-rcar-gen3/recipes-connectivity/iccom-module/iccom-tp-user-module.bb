DESCRIPTION = "Linux ICCOM library test applications for Renesas R-Car Gen3"

require iccom-user-module.inc
require include/multimedia-control.inc
require include/rcar-gen3-path-common.inc

DEPENDS = " \
    kernel-module-iccom-mfis \
    iccom-user-module \
    ${@base_conditional('USE_MULTIMEDIA', '1', 'mmngr-user-module', '', d )} \
"

export USE_MMNGR="${USE_MULTIMEDIA}"

PN = "iccom-tp-user-module"
PR = "r0"

S = "${WORKDIR}/libiccom"

do_compile() {
    cd ${S}/sample_test
    make

    cd ${S}/sample_test_fatal
    make

    cd ${S}/sample_sharedmem
    make

    cd ${S}/sample_lock
    make
}

do_install() {
    # Create destination directory
    install -d ${D}${RENESAS_DATADIR}/bin

    # Install test applications
    install -m 755 ${S}/sample_test/sample_test ${D}${RENESAS_DATADIR}/bin/
    install -m 755 ${S}/sample_test_fatal/sample_test_fatal ${D}${RENESAS_DATADIR}/bin/
    install -m 755 ${S}/sample_sharedmem/sample_sharedmem ${D}${RENESAS_DATADIR}/bin/
    install -m 755 ${S}/sample_lock/sample_lock ${D}${RENESAS_DATADIR}/bin/
}

FILES_${PN} = " \
    ${RENESAS_DATADIR}/bin/sample_test \
    ${RENESAS_DATADIR}/bin/sample_test_fatal \
    ${RENESAS_DATADIR}/bin/sample_sharedmem \
    ${RENESAS_DATADIR}/bin/sample_lock \
"

# Skip debug split
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

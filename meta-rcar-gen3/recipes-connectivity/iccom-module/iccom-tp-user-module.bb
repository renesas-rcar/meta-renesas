DESCRIPTION = "Linux ICCOM library test applications for Renesas R-Car Gen3"

require iccom-user-module.inc
require include/omx-options.inc

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
    install -d ${D}/usr/local/bin

    # Install test applications
    install -m 755 ${S}/sample_test/sample_test ${D}/usr/local/bin/
    install -m 755 ${S}/sample_test_fatal/sample_test_fatal ${D}/usr/local/bin/
    install -m 755 ${S}/sample_sharedmem/sample_sharedmem ${D}/usr/local/bin/
    install -m 755 ${S}/sample_lock/sample_lock ${D}/usr/local/bin/
}

FILES_${PN} = " \
    /usr/local/bin/sample_test \
    /usr/local/bin/sample_test_fatal \
    /usr/local/bin/sample_sharedmem \
    /usr/local/bin/sample_lock \
"

# Skip debug split
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

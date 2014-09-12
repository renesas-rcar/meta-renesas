require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "mmngrbuf-kernel-module mmngrbuf-user-module"
SRC_URI = "file://mmngrbuf-tp-user.tar.bz2"

S = "${WORKDIR}/mmngrbuf"

do_compile() {
    # Build test kernel module
    cd ${S}
    make all ARCH=arm
}

do_install() {
    # Copy kernel test program
    mkdir -p ${D}/usr/local/bin/
    cp ${S}/mmbuftp ${D}/usr/local/bin/
}

PACKAGES = "\
    ${PN} \
"
FILES_${PN} = " \
    /usr/local/bin/mmbuftp \
"

RPROVIDES_${PN} += "mmngrbuf-tp-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "mmngr-kernel-module mmngr-user-module"
SRC_URI = "file://mmngr-tp-user.tar.bz2"

S = "${WORKDIR}/mmngr"

do_compile() {
    # Build test kernel module
    cd ${S}
    make all ARCH=arm
}

do_install() {
    # Copy kernel test program
    mkdir -p ${D}/usr/local/bin/
    cp ${S}/mmtp ${D}/usr/local/bin/
}

PACKAGES = "\
    ${PN} \
"
FILES_${PN} = " \
    /usr/local/bin/mmtp \
"

RPROVIDES_${PN} += "mmngr-tp-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

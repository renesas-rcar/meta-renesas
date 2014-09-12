require ../../include/rcar-gen2-modules-common.inc

S = "${WORKDIR}"

LICENSE = "CLOSED"
DEPENDS = "fdpm-user-module mmngr-user-module"
SRC_URI = "file://fdpm-tp-user.tar.bz2"

do_compile() {
    cd ${S}/fdpm/
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/usr/local/bin/

    # Copy user test program
    cp ${S}/fdpm/fdpm_tp ${D}/usr/local/bin/
}

PACKAGES = "\
    ${PN} \
"

FILES_${PN} = " \
    /usr/local/bin/fdpm_tp \
"

RPROVIDES_${PN} += "fdpm-tp-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

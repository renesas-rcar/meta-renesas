DESCRIPTION = "FLAC decoder library"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING.Xiph;md5=a2c4b71c0198682376d483eb5bcc9197"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/renesas-rcar/flac_decoder.git;branch=master"
SRCREV = "70c0a7ae7dc8cac2056adf5eb175e3c54f892c27"

S = "${WORKDIR}/git"
B = "${S}/build/linux_armyocto/lib"

EXTRA_OEMAKE = "ARCH=${ARCH}"

ARCH = "arm"
ARCH_aarch64 = "arm64"

do_install() {
    # Create directories
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}

    # Install library
    install -m 755 ${S}/build/linux_armyocto/libFLACDLA_L.so.2.0 ${D}/${libdir}/

    # Create symbolic link
    cd ${D}/${libdir}
    ln -s libFLACDLA_L.so.2.0 libFLACDLA_L.so.2
    ln -s libFLACDLA_L.so.2 libFLACDLA_L.so

    # Install shared header file
    install -m 644 ${S}/lib/flacd_Lib.h ${D}/${includedir}/
}

FILES_${PN} += " \
    ${libdir}/libFLACDLA_L.so.* \
    ${libdir}/libFLACDLA_L.so \
"

FILES_${PN}-dev = "${includedir}/*.h"

INSANE_SKIP_${PN} += "dev-so"

# Skip debug strip of do_populate_sysroot()
INHIBIT_SYSROOT_STRIP = "1"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

DESCRIPTION = "ALAC decoder library"
SECTION = "libs"
LICENSE = "Apache-2.0 & APSL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=5cf67868b9e038eccb149ec80809d9f5 \
    file://APPLE_LICENSE.txt;md5=b180a94f894d2a868d40ea43da2bbaba \
"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = " \
    kernel-module-mmngr mmngr-user-module \
    vspmif-user-module kernel-module-vspm-if \
    kernel-module-vspm kernel-module-vsp2driver \
"

RDEPENDS:${PN} += "mmngr-user-module vspmif-user-module"

SRC_URI = "git://github.com/renesas-rcar/alac_decoder.git;branch=master;protocol=https"
SRCREV = "5d7e5c91a932bedb36284591612bfbb3342cd672"

S = "${WORKDIR}/git"
B = "${S}/build/linux_armyocto/lib"

EXTRA_OEMAKE = "ARCH=${ARCH}"

ARCH = "arm"
ARCH:aarch64 = "arm64"

do_install() {
    # Create directories
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}

    # Install library
    install -m 755 ${S}/build/linux_armyocto/libALACDLA_L.so.2.0 ${D}/${libdir}/

    # Create symbolic link
    cd ${D}/${libdir}
    ln -s libALACDLA_L.so.2.0 libALACDLA_L.so.2
    ln -s libALACDLA_L.so.2 libALACDLA_L.so

    # Install shared header file
    install -m 644 ${S}/lib/alacd_Lib.h ${D}/${includedir}/
}

FILES:${PN} += " \
    ${libdir}/libALACDLA_L.so.* \
    ${libdir}/libALACDLA_L.so \
"

FILES:${PN}-dev = "${includedir}/*.h"

INSANE_SKIP:${PN} += "dev-so"

#To avoid already-stripped errors and not stripped libs from packages
INSANE_SKIP:${PN} += "already-stripped"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

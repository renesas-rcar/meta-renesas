DESCRIPTION = "Linux iccom library"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=442d4e9f738ff4d05ae6215ae20caa6c"

inherit features_check

PN = "iccom-user-module"
PR = "r0"

REQUIRED_DISTRO_FEATURES = "iccom"

SRC_URI = "file://linux_iccom_library.tar.gz"
S = "${WORKDIR}/linux_iccom_library"

COMPATIBLE_MACHINE = "salvator-x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile() {
    cd ${S}/
    oe_runmake
}

do_install () {
    # Create destination folders
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${bindir}

    # Copy library
    install -m 0755 ${S}/out/libiccom.so.1.0 ${D}${libdir}
    install -m 0644 ${S}/public/iccom.h ${D}${includedir}

    # Create symbolic link
    cd ${D}${libdir}
    ln -sf libiccom.so.1.0 libiccom.so.1
    ln -sf libiccom.so.1 libiccom.so

    # Install sample app
    install -m 0755 ${S}/out/iccom-test ${D}${bindir}
    install -m 0755 ${S}/out/iccom-sample-app ${D}${bindir}
}

PACKAGES = " \
    ${PN} \
    ${PN}-dbg \
"

FILES:${PN} += " \
    ${libdir}/* \
    ${includedir}/* \
    ${bindir}/* \
"

FILES:${PN}-dbg = ""
ALLOW_EMPTY:${PN}-dbg = "1"
INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN}-dbg = "buildpaths"

RPROVIDES:${PN} += "linux-iccomlib"

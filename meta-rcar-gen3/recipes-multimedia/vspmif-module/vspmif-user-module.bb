DESCRIPTION = "VSP Manager Interface library for R-Car Gen3"

require vspmif.inc

DEPENDS = "kernel-module-vspmif mmngr-user-module"
PN = "vspmif-user-module"
PR = "r0"

S = "${WORKDIR}/git"
VSPMIF_LIB_DIR = "vspm_if-module/files/vspm_if"

EXTRA_OEMAKE = "ARCH=${TARGET_ARCH}"

includedir = "${RENESAS_DATADIR}/include"

do_compile() {
    export VSPM_LEGACY_IF="1"

    # Build shared library
    cd ${S}/${VSPMIF_LIB_DIR}/if
    rm -rf ${S}/${VSPMIF_LIB_DIR}/if/libvspm.so*
    oe_runmake
}

do_install() {
    # Create destination folders
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}

    # Copy shared library
    install -m 755 ${S}/${VSPMIF_LIB_DIR}/if/libvspm.so* ${D}/${libdir}/
    cd ${D}/${libdir}/
    ln -sf libvspm.so.1.0.0 libvspm.so.1
    ln -sf libvspm.so.1 libvspm.so

    # Copy shared header files
    install -m 644 ${S}/${VSPMIF_LIB_DIR}/include/vspm_public.h ${D}/${includedir}/
    install -m 644 ${S}/${VSPMIF_LIB_DIR}/include/fdpm_api.h ${D}/${includedir}/
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

RPROVIDES_${PN} += "vspmif-user-module"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"

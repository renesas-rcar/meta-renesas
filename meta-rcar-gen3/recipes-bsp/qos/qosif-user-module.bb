DESCRIPTION = "QOS Interface library for R-Car Gen3"

require qosif.inc

DEPENDS = "kernel-module-qos"
PN = "qosif-user-module"
PR = "r0"

S = "${WORKDIR}/git"
QOSIF_LIB_DIR = "qos_if-module/files/qos_if/if"

EXTRA_OEMAKE = "ARCH=${TARGET_ARCH}"

do_compile() {
    # Build shared library
    cd ${S}/${QOSIF_LIB_DIR}
    rm -rf ${S}/${QOSIF_LIB_DIR}/libqos.so*
    oe_runmake
}

do_install() {
    # Create destination directories
    install -d ${D}/${libdir}
    install -d ${D}/usr/local/include

    # Copy shared library
    install -m 755 ${S}/${QOSIF_LIB_DIR}/libqos.so* ${D}/${libdir}/
    cd ${D}/${libdir}/
    ln -sf libqos.so.1.0.0 libqos.so.1
    ln -sf libqos.so.1 libqos.so

    # Copy shared header files
    install -m 644 ${BUILDDIR}/include/qos_public.h ${D}/usr/local/include
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES_${PN} = " \
    ${libdir}/libqos.so* \
"

FILES_${PN}-dev = " \
    /usr/local/include \
    /usr/local/include/*.h \
    ${libdir}/libqos.so* \
"

FILES_${PN}-dbg = " \
    ${libdir}/.debug/* \
"

INSANE_SKIP_${PN} = "dev-so"

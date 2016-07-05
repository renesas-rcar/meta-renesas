DESCRIPTION = "QOS Interface library for R-Car Gen3"

require qosif.inc

DEPENDS = "kernel-module-qos"
PN = "qosif-user-module"
PR = "r0"

S = "${WORKDIR}/git"
QOSIF_LIB_DIR = "qos_if-module/files/qos_if/if"

EXTRA_OEMAKE = "ARCH=${TARGET_ARCH}"

SRC_URI += "file://0001-Do-not-copy-header-files-to-builddir.patch"

includedir="/usr/local/include"

# do_configure() nothing
do_configure[noexec] = "1"

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
    install -m 644 ${S}/qos_if-module/files/qos_if/include/qos_public.h ${D}/usr/local/include
}


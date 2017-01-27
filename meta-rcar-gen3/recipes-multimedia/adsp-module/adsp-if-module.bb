DESCRIPTION = "ADSP Interface for Linux"
LICENSE = "CLOSED"

require include/adsp-control.inc

DEPENDS += "kernel-module-xtensa-hifi"

SRC_URI = "${@base_conditional('USE_ADSP', '1', 'file://RCG3AHIFL4001ZDP.tar.gz', '', d )}"

S = "${WORKDIR}/RCG3AHIFL4001ZDP"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Don't need to do_configure
do_configure[noexec] = "1"

# Don't use EXTRA_OEMAKE default value to avoid default CFLAGS, LDFLAGS
# of poky override the CFLAGS, LDFLAGS inside RCG3AHIFL4001ZDP/Makefile
EXTRA_OEMAKE = "INCSHARED=${STAGING_INCDIR}"

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    # Create destination directory
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}
    install -d ${D}/${includedir}/sys/fio
    install -d ${D}/${includedir}/os/linux

    # Copy library
    install -m 0755 ${S}/libRCG3AHIFL4001ZDP.so.1.0 ${D}/${libdir}

    # Copy shared header files
    install -m 0644 ${S}/include/*.h ${D}/${includedir}
    install -m 0644 ${S}/include/sys/fio/*.h ${D}/${includedir}/sys/fio/
    install -m 0644 ${S}/include/os/linux/*.h ${D}/${includedir}/os/linux/

    # Create the symbolic link
    cd ${D}/${libdir}
    ln -s libRCG3AHIFL4001ZDP.so.1.0 libRCG3AHIFL4001ZDP.so.1
    ln -s libRCG3AHIFL4001ZDP.so.1 libRCG3AHIFL4001ZDP.so
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

INSANE_SKIP_${PN} += "dev-so"

FILES_${PN} += " \
    ${libdir}/*.so* \
    ${includedir} \
"

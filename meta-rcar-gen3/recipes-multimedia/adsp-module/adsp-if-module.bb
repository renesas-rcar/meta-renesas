DESCRIPTION = "ADSP Interface for Linux"
LICENSE = "CLOSED"

inherit features_check

REQUIRED_DISTRO_FEATURES = "adsp"

DEPENDS += "kernel-module-xtensa-hifi"

SRC_URI = "file://RTM8RC0000ZNA1SS00JFL3E.tar.gz"

S = "${WORKDIR}/RTM8RC0000ZNA1SS00JFL3E"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Don't need to do_configure
do_configure[noexec] = "1"

# Don't use EXTRA_OEMAKE default value to avoid default CFLAGS, LDFLAGS
# of poky override the CFLAGS, LDFLAGS inside RTM8RC0000ZNA1SS00JFL3E/Makefile
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
    install -m 0755 ${S}/libRCG3AHIFL4101ZDP.so.1.0 ${D}/${libdir}

    # Copy shared header files
    install -m 0644 ${S}/include/*.h ${D}/${includedir}
    install -m 0644 ${S}/include/sys/fio/*.h ${D}/${includedir}/sys/fio/
    install -m 0644 ${S}/include/os/linux/*.h ${D}/${includedir}/os/linux/

    # Create the symbolic link
    cd ${D}/${libdir}
    ln -s libRCG3AHIFL4101ZDP.so.1.0 libRCG3AHIFL4101ZDP.so.1
    ln -s libRCG3AHIFL4101ZDP.so.1 libRCG3AHIFL4101ZDP.so
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

INSANE_SKIP:${PN} += "dev-so"

FILES:${PN} += " \
    ${libdir}/*.so* \
    ${includedir} \
"

DESCRIPTION = "OP-TEE Client"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f0fb2f357d31d6a98213b19f57abf927"
PN = "optee-client"
PR = "r0"
BRANCH = "master"
SRC_URI = \
    "git://github.com/OP-TEE/optee_client.git;protocol=https;branch=${BRANCH}"
SRCREV = "9e29f6081db7904a057b7c336c4604c144b374f6"

SRC_URI += " \
    file://0001-add-optee_client-R-Car-support.patch \
"

PV = "1.0.0+renesas+git${SRCPV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile() {
    cd ${S}/
    oe_runmake
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/out/export/bin/tee-supplicant ${D}${bindir}

    install -d ${D}${libdir}
    install -m 0755 ${S}/out/export/lib/libteec.so.1.0 ${D}${libdir}
    cd ${D}${libdir}
    ln -sf libteec.so.1.0 libteec.so.1
    ln -sf libteec.so.1 libteec.so

    install -d ${D}${includedir}
    install -m 0644 ${S}/out/export/include/* ${D}${includedir}
}

RPROVIDES_${PN} += "optee-client"

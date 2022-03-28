DESCRIPTION = "gptp - an AVnu sponsored repository for Time Sensitive Network (TSN and AVB) technology"
HOMEPAGE = "https://github.com/AVnu/gptp"
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=b3e2a6e620763288bcbc3190f6cb1704 \
"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"

PV = "1.1+git${SRCPV}"

SRCREV = "0baef8a36a13105112862919aac0f1eed21a44ea"
SRC_URI = "git://github.com/AVnu/gptp.git;branch=master;protocol=https"

# Fix QA issue of ldflags
SRC_URI:append = " file://0001-Fix-QA-issue-when-build-with-Yocto.patch"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CC=${CC}' 'CXX=${CXX}'"

do_configure() {
    cd ${S}/linux/build
    oe_runmake clean
}

do_compile() {
    cd ${S}/linux/build
    oe_runmake all
}

do_install() {
    install -d ${D}/${bindir}
    install -m 755 linux/build/obj/daemon_cl ${D}/${bindir}
}

DESCRIPTION = "Utility tool of the AVB Streaming Driver for Linux for the R-Car Gen3"

require avb-applications.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=96659f2a7571bfa27483882a653c3bb9"

DEPENDS = "kernel-module-avb-streaming"

S = "${WORKDIR}/git/avbtool"

EXTRA_OEMAKE = "'CC=${CC}'"

do_install_append() {
    install -d ${D}/${bindir}
    install -m 755 ${S}/avbtool ${D}/${bindir}
}

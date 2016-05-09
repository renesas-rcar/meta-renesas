DESCRIPTION = "Renesas Ethernet AVB demo applications"

require avb-applications.inc

LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=96659f2a7571bfa27483882a653c3bb9 \
    file://lib/msrp/LICENSE.BSD;md5=60ba0ea0afdcbf8d26ce31ce1503aba3 \
    file://avblauncher/inih/LICENSE.txt;md5=a7a95d2af90376e85a05318794e6f202 \
"

DEPENDS = "kernel-module-avb-streaming"

S = "${WORKDIR}/git/avb-demoapps"

EXTRA_OEMAKE = "'CC=${CC}' 'AR=${AR}' 'SYSROOT=${STAGING_DIR_HOST}'"

do_install_append() {
    oe_runmake install INSTALL_DIR=${D}/${bindir}
    install -d ${D}/${sysconfdir}/linuxptp
    install -d ${D}/${sysconfdir}/daemon_cl
    install -d ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/linuxptp/default.cfg ${D}/${sysconfdir}/linuxptp
    install -m 644 ${S}/etc/linuxptp/gPTP.cfg ${D}/${sysconfdir}/linuxptp
    install -m 644 ${S}/etc/linuxptp/avb-demoapps.cfg ${D}/${sysconfdir}/linuxptp
    install -m 644 ${S}/etc/daemon_cl/gptp_cfg_100mb.ini ${D}/${sysconfdir}/daemon_cl
    install -m 644 ${S}/etc/daemon_cl/gptp_cfg.ini ${D}/${sysconfdir}/daemon_cl
    install -m 644 ${S}/etc/avblauncher/simple_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/simple_listener.ini ${D}/${sysconfdir}/avblauncher
}

PACKAGES =+ " \
    ${PN}-simple \
    ${PN}-mrpdummy \
    ${PN}-avblauncher \
"

ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-mrpdummy = " \
    ${bindir}/mrpdummy \
"

FILES_${PN}-avblauncher = " \
    ${sysconfdir}/daemon_cl/gptp_cfg_100mb.ini \
    ${sysconfdir}/daemon_cl/gptp_cfg.ini \
    ${sysconfdir}/avblauncher/simple_talker.ini \
    ${sysconfdir}/avblauncher/simple_listener.ini \
    ${bindir}/avblauncher \
"

FILES_${PN}-simple = " \
    ${sysconfdir}/linuxptp/default.cfg \
    ${sysconfdir}/linuxptp/gPTP.cfg \
    ${sysconfdir}/linuxptp/avb-demoapps.cfg \
    ${bindir}/simple_talker \
    ${bindir}/simple_listener \
"

RDEPENDS_${PN} = " \
    ${PN}-simple \
    ${PN}-avblauncher \
"

RDEPENDS_${PN}-avblauncher = " \
    ${PN}-mrpdummy \
"

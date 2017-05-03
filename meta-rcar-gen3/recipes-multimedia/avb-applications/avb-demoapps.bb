DESCRIPTION = "Renesas Ethernet AVB demo applications"

require avb-applications.inc

LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=96659f2a7571bfa27483882a653c3bb9 \
    file://lib/msrp/LICENSE.BSD;md5=60ba0ea0afdcbf8d26ce31ce1503aba3 \
    file://lib/avdecc/jdksavdecc-c/COPYRIGHT;md5=6592ded5140540a936b8d98ed1b6a577 \
    file://avblauncher/inih/LICENSE.txt;md5=a7a95d2af90376e85a05318794e6f202 \
"

DEPENDS = "kernel-module-avb-streaming libyaml"

S = "${WORKDIR}/git/avb-demoapps"

includedir = "${RENESAS_DATADIR}/include"

# submodule is extracted before do_populate_lic
addtask do_init_submodule after do_unpack before do_patch

do_init_submodule() {
    export http_proxy=${http_proxy}
    export https_proxy=${https_proxy}
    export HTTP_PROXY=${HTTP_PROXY}
    export HTTPS_PROXY=${HTTPS_PROXY}
    cd ${S}
    git submodule init
    git submodule update
}

EXTRA_OEMAKE = "'CC=${CC}' 'AR=${AR}'"

do_install_append() {
    oe_runmake install INSTALL_DIR=${D}/${bindir}

    # Create intall directories
    install -d ${D}/${sysconfdir}/linuxptp
    install -d ${D}/${sysconfdir}/daemon_cl
    install -d ${D}/${sysconfdir}/avblauncher

    # Install
    install -m 644 ${S}/etc/linuxptp/avb-demoapps.cfg ${D}/${sysconfdir}/linuxptp
    install -m 644 ${S}/etc/daemon_cl/gptp_cfg_100mb.ini ${D}/${sysconfdir}/daemon_cl
    install -m 644 ${S}/etc/daemon_cl/gptp_cfg.ini ${D}/${sysconfdir}/daemon_cl
    install -m 755 ${S}/etc/avblauncher/mse_aaf_pcm.sh ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_aaf_pcm_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_aaf_pcm_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 755 ${S}/etc/avblauncher/mse_cvf_h264.sh ${D}/${sysconfdir}/avblauncher
    install -m 755 ${S}/etc/avblauncher/mse_cvf_h264_d13.sh ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_cvf_h264_d13_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_cvf_h264_d13_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_cvf_h264_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_cvf_h264_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 755 ${S}/etc/avblauncher/mse_cvf_mjpeg.sh ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_cvf_mjpeg_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_cvf_mjpeg_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 755 ${S}/etc/avblauncher/mse_iec61883_4.sh ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_iec61883_4_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_iec61883_4_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 755 ${S}/etc/avblauncher/mse_iec61883_6.sh ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_iec61883_6_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/mse_iec61883_6_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 755 ${S}/etc/avblauncher/simple_application.sh ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/simple_listener.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/simple_talker.ini ${D}/${sysconfdir}/avblauncher
    install -m 644 ${S}/etc/avblauncher/avdecc_entity_audio.yaml ${D}/${sysconfdir}/avblauncher
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
    ${sysconfdir}/avblauncher/mse_aaf_pcm.sh \
    ${sysconfdir}/avblauncher/mse_aaf_pcm_listener.ini \
    ${sysconfdir}/avblauncher/mse_aaf_pcm_talker.ini \
    ${sysconfdir}/avblauncher/mse_cvf_h264.sh \
    ${sysconfdir}/avblauncher/mse_cvf_h264_d13.sh \
    ${sysconfdir}/avblauncher/mse_cvf_h264_d13_listener.ini \
    ${sysconfdir}/avblauncher/mse_cvf_h264_d13_talker.ini \
    ${sysconfdir}/avblauncher/mse_cvf_h264_listener.ini \
    ${sysconfdir}/avblauncher/mse_cvf_h264_talker.ini \
    ${sysconfdir}/avblauncher/mse_cvf_mjpeg.sh \
    ${sysconfdir}/avblauncher/mse_cvf_mjpeg_listener.ini \
    ${sysconfdir}/avblauncher/mse_cvf_mjpeg_talker.ini \
    ${sysconfdir}/avblauncher/mse_iec61883_4.sh \
    ${sysconfdir}/avblauncher/mse_iec61883_4_listener.ini \
    ${sysconfdir}/avblauncher/mse_iec61883_4_talker.ini \
    ${sysconfdir}/avblauncher/mse_iec61883_6.sh \
    ${sysconfdir}/avblauncher/mse_iec61883_6_listener.ini \
    ${sysconfdir}/avblauncher/mse_iec61883_6_talker.ini \
    ${sysconfdir}/avblauncher/simple_application.sh \
    ${sysconfdir}/avblauncher/simple_listener.ini \
    ${sysconfdir}/avblauncher/simple_talker.ini \
    ${sysconfdir}/avblauncher/avdecc_entity_audio.yaml \
    ${bindir}/avblauncher \
"

FILES_${PN}-simple = " \
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

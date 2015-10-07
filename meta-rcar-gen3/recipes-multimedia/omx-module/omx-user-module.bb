DESCRIPTION = "OMX Media Components R-Car Gen3"
LICENSE = "CLOSED"

DEPENDS = " \
    kernel-module-mmngr mmngr-user-module \
    vspmif-user-module kernel-module-vspmif \
    kernel-module-vspm kernel-module-vsp2driver \
    kernel-module-uvcs \
"

require include/omx-options.inc
inherit autotools

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI_H264D = '${@base_conditional("USE_H264D_OMX", "1", "file://RTM0AC0000XV264D30SL40C.tar.bz2", "", d )}'
SRC_URI_ACMND = '${@base_conditional("USE_AUDIO_OMX", "1", "file://RTM0AC0000XACMND30SL40C.tar.gz", "", d )}'
SRC_URI_AACLC = '${@base_conditional("USE_AACLCD_OMX", "1", "file://RTM0AC0000XAAACD30SL40C.tar.gz", "", d )}'
SRC_URI_AACMZ = '${@base_conditional("USE_AACLC_MDW", "1", "file://RTM0AC0000ADAACMZ1SL40C.tar.gz", "", d )}'

SRC_URI = " \
    file://RTM0AC0000XCMCTL30SL40C.tar.bz2;unpack=0 \
    file://RTM0AC0000XVCMND30SL40C.tar.bz2;unpack=0 \
    ${SRC_URI_H264D} \
    ${SRC_URI_ACMND} \
    ${SRC_URI_AACLC} \
    ${SRC_URI_AACMZ} \
"

OMX_H264_DEC += '${@base_conditional("USE_H264D_OMX", "1", "RTM0AC0000XV264D30SL40C", "", d )}'

OMX_VIDEO_SRC = " \
    RTM0AC0000XCMCTL30SL40C \
    RTM0AC0000XVCMND30SL40C \
"

AAC_MIDDLEWARE = '${@base_conditional("USE_AACLC_MDW", "1", "RTM0AC0000ADAACMZ1SL40C", "", d )}'

OMX_AUDIO_COMMON = '${@base_conditional("USE_AUDIO_OMX", "1", "RTM0AC0000XACMND30SL40C", "", d )}'
OMX_AACLC = '${@base_conditional("USE_AACLCD_OMX", "1", "RTM0AC0000XAAACD30SL40C", "", d )}'

OMX_AUDIO_SRC = " \
    ${OMX_AUDIO_COMMON} \
    ${OMX_AACLC} \
"

S = "${WORKDIR}/omx/"

do_unpack_prepend() {
    bb.build.exec_func('setup_src_dir', d)
}

setup_src_dir() {
    install -d ${WORKDIR}/omx
}

do_unpack_append() {
    bb.build.exec_func('setup_build_tree', d)
}

setup_build_tree() {
    for omxmc in ${OMX_VIDEO_SRC}
    do
        tar xf ${WORKDIR}/${omxmc}.tar.bz2 -C ${WORKDIR}
        tar xf ${WORKDIR}/${omxmc}.tar.bz2 ${omxmc}/src --strip=2 -C ${WORKDIR}/omx
        tar xf ${WORKDIR}/${omxmc}.tar.bz2 ${omxmc}/include --strip=1 -C ${WORKDIR}/omx
    done
}

B = "${S}"

EXTRA_OECONF = "OMXR_DEFAULT_CONFIG_FILE_NAME=${sysconfdir}/omxr/omxr_config_base.txt"

do_configure() {
    chmod u+x autogen.sh
    ./autogen.sh
    oe_runconf
}

do_install_omx_video() {

    cd ${D}${libdir}
    for omxmc in ${OMX_VIDEO_SRC} ${OMX_H264_DEC}
    do
        src="${WORKDIR}/${omxmc}"
        install -m 755 ${src}/${baselib}/lib*.so.* ${D}${libdir}
        install -m 644 ${src}/include/*.h ${D}${includedir}
        install -m 644 ${src}/config/*.txt ${D}${sysconfdir}/omxr
    done

    ln -s libomxr_core.so.3.0.0 libomxr_core.so.3
    ln -s libomxr_core.so.3 libomxr_core.so

    ln -s libomxr_mc_cmn.so.3.0.0 libomxr_mc_cmn.so.3
    ln -s libomxr_mc_cmn.so.3 libomxr_mc_cmn.so

    ln -s libomxr_mc_vcmn.so.3.0.0 libomxr_mc_vcmn.so.3
    ln -s libomxr_mc_vcmn.so.3 libomxr_mc_vcmn.so

    ln -s libomxr_mc_vdcmn.so.3.0.0 libomxr_mc_vdcmn.so.3
    ln -s libomxr_mc_vdcmn.so.3 libomxr_mc_vdcmn.so

    ln -s libuvcs_dec.so.3.0.0 libuvcs_dec.so.3
    ln -s libuvcs_dec.so.3 libuvcs_dec.so

    if [ "X${USE_H264D_OMX}" = "X1" ]; then
        ln -s libomxr_mc_h264d.so.3.0.0 libomxr_mc_h264d.so.3
        ln -s libomxr_mc_h264d.so.3 libomxr_mc_h264d.so

        ln -s libuvcs_avcd.so.3.0.0 libuvcs_avcd.so.3
        ln -s libuvcs_avcd.so.3 libuvcs_avcd.so
    fi
}

do_install_audio_middleware() {
    cd ${D}${libdir}
    if [ "X${USE_AACLC_MDW}" = "X1" ]; then
        install -m 755 ${WORKDIR}/${AAC_MIDDLEWARE}/${baselib}/libAACDLA_L.so.3.0 \
        ${D}${libdir}
        install -m 644 ${WORKDIR}/${AAC_MIDDLEWARE}/include/*.h ${D}${includedir}

        ln -s libAACDLA_L.so.3.0 libAACDLA_L.so.3
        ln -s libAACDLA_L.so.3 libAACDLA_L.so
    fi
}

do_install_omx_audio() {
    cd ${D}${libdir}
    for omxmc in ${OMX_AUDIO_SRC}
    do
        src="${WORKDIR}/${omxmc}/"
        install -m 755 ${src}/${baselib}/lib*.so.* \
        ${D}${libdir}
        if [ -d ${src}/include ]; then
            install -m 644 ${src}/include/*.h ${D}${includedir}
        fi
        install -m 644 ${src}/config/*.txt ${D}${sysconfdir}/omxr
    done

    if [ "X${USE_AUDIO_OMX}" = "X1" ]; then
        ln -s libomxr_mc_acmn.so.3.0.0 libomxr_mc_acmn.so.3
        ln -s libomxr_mc_acmn.so.3 libomxr_mc_acmn.so
    fi

    if [ "X${USE_AACLCD_OMX}" = "X1" ]; then
        ln -s libomxr_mc_aacd.so.3.0.0 libomxr_mc_aacd.so.3
        ln -s libomxr_mc_aacd.so.3 libomxr_mc_aacd.so
    fi
}

do_install_append() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${sysconfdir}/omxr

    do_install_omx_video
    do_install_audio_middleware
    do_install_omx_audio
}

INSANE_SKIP_${PN}= "already-stripped"
INSANE_SKIP_${PN} += "dev-so"

FILES_${PN} += " \
    ${libdir}/*.so \
"

FILES_${PN}-dev = " \
    ${includedir} \
    ${libdir}/*.la \
"

RDEPENDS_${PN} += "mmngr-user-module vspmif-user-module"

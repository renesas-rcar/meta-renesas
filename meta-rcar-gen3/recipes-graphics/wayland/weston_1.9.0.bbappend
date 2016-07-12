require include/omx-options.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_rcar-gen3 = " \
    git://github.com/renesas-rcar/weston.git;protocol=git;branch=rcar-gen3/1.9.0/v4l2-renderer \
    file://weston.png \
    file://weston.desktop \
    file://make-lcms-explicitly-configurable.patch \
    file://make-libwebp-explicitly-configurable.patch \
    file://0001-make-error-portable.patch \
    file://libsystemd.patch \
    file://explicit-enable-disable-systemd.patch \
"

GL_SRCREV = "02a9ef290df887a815b71a49e8521c7909d7acc1"
V4L2_SRCREV = "3859758c97de269711c80b10164732e6c3db384a"

SRCREV_rcar-gen3 = '${@base_conditional("USE_MULTIMEDIA", "1", "${V4L2_SRCREV}", "${GL_SRCREV}", d)}'

SRC_URI_append_rcar-gen3 = " \
    file://0001-protocol-Add-pkgconfig-file-to-be-referred-from-clie.patch \
    file://0001-configure-don-t-control-egl-version.patch \
    ${@base_conditional("USE_MULTIMEDIA", "1", " \
        file://weston.ini \
        file://0001-vsp2-renderer-use-model-name-to-determine-the-underl.patch", "", d)} \
"

S = "${WORKDIR}/git"

PACKAGECONFIG_append = " \
    ${@base_conditional('USE_MULTIMEDIA', '1', ' v4l2', '', d)} \
"

PACKAGECONFIG[v4l2] = " --enable-v4l2,,libmediactl-v4l2,kernel-module-vsp2driver"

do_install_append_rcar-gen3() {
    # install weston.ini as sample settings of v4l2-renderer
    if [ "X${USE_MULTIMEDIA}" = "X1" ]; then
        # install xml for client applications
        install -d ${D}/${datadir}/wayland-protocols/
        install -m 644 ${S}/protocol/linux-dmabuf.xml ${D}/${datadir}/wayland-protocols/

        # install weston.ini as sample settings of v4l2-renderer
        install -d ${D}/${sysconfdir}/xdg/weston
        install -m 644 ${WORKDIR}/weston.ini ${D}/${sysconfdir}/xdg/weston/
    fi
}

FILES_${PN}_append_rcar-gen3 = " \
    ${@base_conditional("USE_MULTIMEDIA", "1", "${sysconfdir}/xdg/weston/weston.ini", "", d)} \
"

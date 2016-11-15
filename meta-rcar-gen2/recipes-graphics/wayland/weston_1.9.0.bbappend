FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_rcar-gen2 = " \
    git://github.com/renesas-rcar/weston.git;protocol=git;branch=rcar-gen3/1.9.0/v4l2-renderer \
    file://weston.png \
    file://weston.desktop \
    file://make-lcms-explicitly-configurable.patch \
    file://make-libwebp-explicitly-configurable.patch \
    file://0001-make-error-portable.patch \
    file://libsystemd.patch \
    file://explicit-enable-disable-systemd.patch \
    file://0002-remove-unsupported-extensions.patch \
"

GL_SRCREV = "02a9ef290df887a815b71a49e8521c7909d7acc1"
V4L2_SRCREV = "c768fef3ebba70ec410c5dc4d0960b064062dc78"

SRCREV_rcar-gen2 = '${@base_conditional("USE_MULTIMEDIA", "1", "${V4L2_SRCREV}", "${GL_SRCREV}", d)}'

SRC_URI_append_rcar-gen2 = " \
    file://0001-protocol-Add-pkgconfig-file-to-be-referred-from-clie.patch \
    file://0001-configure-don-t-control-egl-version.patch \
    file://weston.ini \
    ${@base_conditional("USE_MULTIMEDIA", "1", " file://weston_v4l2.ini", "", d)} \
    ${@base_conditional("USE_MULTIMEDIA", "1", " file://0003-vsp2-render-correct-number-of-input-formatters.patch", "", d)} \
"

S = "${WORKDIR}/git"

PACKAGECONFIG_append = " \
    ${@base_conditional('USE_MULTIMEDIA', '1', ' v4l2', '', d)} \
"

PACKAGECONFIG[v4l2] = " --enable-v4l2,,libmediactl-v4l2,kernel-module-vsp2"

do_install_append_rcar-gen2() {
        # install xml for client applications
    install -d ${D}/${datadir}/wayland-protocols/
    install -m 644 ${S}/protocol/linux-dmabuf.xml ${D}/${datadir}/wayland-protocols/

    # install weston.ini as sample settings of v4l2-renderer
    if [ "X${USE_MULTIMEDIA}" = "X1" ]; then
        # install weston.ini as sample settings of v4l2-renderer
        install -d ${D}/${sysconfdir}/xdg/weston
        install -m 644 ${WORKDIR}/weston_v4l2.ini ${D}/${sysconfdir}/xdg/weston/weston.ini
    else
        # install weston.ini as sample settings of gl-renderer
        install -d ${D}/${sysconfdir}/xdg/weston
        install -m 644 ${WORKDIR}/weston.ini ${D}/${sysconfdir}/xdg/weston/
    fi
}

FILES_${PN}_append_rcar-gen2 = " \
    ${sysconfdir}/xdg/weston/weston.ini \
"

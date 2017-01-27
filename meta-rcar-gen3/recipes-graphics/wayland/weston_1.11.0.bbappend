require weston.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

BRANCH = "rcar-gen3/1.11.0/gl-fallback"

SRCREV = "ef4fdad7fc6549493c244b6e657ec00565408497"

SRC_URI = " \
    git://github.com/renesas-rcar/weston.git;branch=${BRANCH} \
    file://weston.png \
    file://weston.desktop \
    file://0001-make-error-portable.patch \
    file://0001-configure.ac-Fix-wayland-protocols-path.patch \
    file://0001-shared-include-stdint.h-for-int32_t.patch \
    file://xwayland.weston-start \
    file://make-weston-launch-exit-for-unrecognized-option.patch \
    file://0001-weston-launch-Provide-a-default-version-that-doesn-t.patch \
    file://weston.ini \
    file://weston_v4l2.ini \
"

S = "${WORKDIR}/git"

PACKAGECONFIG_append = " \
    ${@base_conditional('USE_MULTIMEDIA', '1', ' v4l2', '', d)} \
"
PACKAGECONFIG[v4l2] = " --enable-v4l2, --disable-v4l2,,kernel-module-vsp2driver"

do_install_append() {
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

FILES_${PN}_append = " \
    ${sysconfdir}/xdg/weston/weston.ini \
"

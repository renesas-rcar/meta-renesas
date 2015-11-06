SRC_URI = "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;protocol=https;branch=RCAR-GEN3/1.4.5"
SRC_URI += " \
    file://0001-gl-do-not-check-for-GL-GLU-EGL-GLES2-libs-if-disable.patch \
    file://configure-allow-to-disable-libssh2.patch"
SRCREV = "9a9bab15b202813f7ec38d4d981e5aa59af63a1c"

DEPENDS += "wayland-kms"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_${PN} = "libwayland-egl"

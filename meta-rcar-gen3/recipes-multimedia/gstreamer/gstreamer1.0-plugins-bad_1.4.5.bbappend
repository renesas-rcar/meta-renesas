SRC_URI = "gitsm://github.com/renesas-rcar/gst-plugins-bad.git;protocol=https;branch=RCAR-GEN3/1.4.5"
SRC_URI += " \
    file://0001-gl-do-not-check-for-GL-GLU-EGL-GLES2-libs-if-disable.patch \
    file://configure-allow-to-disable-libssh2.patch"
SRCREV = "c313fe2ca48af30688f9e0b9c4f0f6b9c9e97b16"

DEPENDS += "wayland-kms"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_${PN} = "libwayland-egl"

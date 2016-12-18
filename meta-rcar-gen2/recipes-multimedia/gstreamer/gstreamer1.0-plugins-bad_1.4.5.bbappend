SRC_URI = "git://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3/1.4.5"
SRC_URI += " \
    file://0001-gl-do-not-check-for-GL-GLU-EGL-GLES2-libs-if-disable.patch \
    file://configure-allow-to-disable-libssh2.patch"
SRCREV = "3c3ebbb4b14ebf5f311a66d7e43b71e8ab32388a"

DEPENDS += "weston"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_${PN} = "libwayland-egl"

SRC_URI = "git://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3/1.6.3"
SRC_URI += " \
    file://configure-allow-to-disable-libssh2.patch \
    file://0001-glimagesink-Downrank-to-marginal.patch \
    file://0002-glplugin-glwindow-fix-memory-leak-of-navigation-thre.patch \
"
SRCREV = "3143b25ca69f1f6bacdac0a33248007c979ba4a5"

DEPENDS += "weston"

S = "${WORKDIR}/git"

# submodule is extracted before do_populate_lic
addtask do_init_submodule after do_unpack before do_patch

do_init_submodule() {
    cd ${S}
    git submodule init
    git submodule update
}

do_configure_prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

RDEPENDS_${PN} = "libwayland-egl"

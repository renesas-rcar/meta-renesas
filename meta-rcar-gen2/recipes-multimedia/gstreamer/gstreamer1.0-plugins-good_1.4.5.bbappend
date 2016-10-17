SRC_URI = "git://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3/1.4.5"

# patches come from poky
SRC_URI += " \
    file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
    file://decrease_asteriskh263_rank.patch \
"

SRCREV = "5aedbd67d8cfb0faf42602f8abb0c9049b50c8e7"

S = "${WORKDIR}/git"

EXTRA_OECONF_append = " --enable-cont-frame-capture"

# submodule is extracted before do_populate_lic
do_unpack_append() {
    bb.build.exec_func('do_init_submodule', d)
}

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

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar = " \
    file://wired.network \
"

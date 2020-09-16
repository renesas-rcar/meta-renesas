FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen3 = " \
    file://wired.network \
"

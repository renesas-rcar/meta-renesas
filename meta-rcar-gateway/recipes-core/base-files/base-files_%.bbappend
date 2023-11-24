FILESEXTRAPATHS_prepend_rcar-gateway := "${THISDIR}/${PN}/:"

SRC_URI_append_rcar-gateway = " \
    file://fstab \
"


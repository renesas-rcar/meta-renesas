FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar = " file://add-non-owned-item-for-safe-transitioning.patch"

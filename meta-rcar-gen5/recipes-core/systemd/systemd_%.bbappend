FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen5 = " file://add-non-owned-item-for-safe-transitioning.patch"

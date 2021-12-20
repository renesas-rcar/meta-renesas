FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://add-non-owned-item-for-safe-transitioning.patch"

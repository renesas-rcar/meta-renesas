FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://add-non-owned-item-for-safe-transitioning.patch"

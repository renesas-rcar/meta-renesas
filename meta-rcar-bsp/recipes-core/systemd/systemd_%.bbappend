FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar = " file://add-non-owned-item-for-safe-transitioning.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen3 = " file://add-non-owned-item-for-safe-transitioning.patch"

PACKAGECONFIG_remove_rcar-gen3 = "timesyncd"


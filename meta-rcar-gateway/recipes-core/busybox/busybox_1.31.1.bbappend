FILESEXTRAPATHS_prepend_rcar-gateway := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gateway = " file://0001-stty-Add-support-for-non-standard-baudrates.patch"

# Make stty's priority higher than coreutils
ALTERNATIVE_PRIORITY_rcar-gateway[stty] = "110"

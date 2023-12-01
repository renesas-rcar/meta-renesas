FILESEXTRAPATHS:prepend:rcar-gateway := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gateway = " file://0001-stty-Add-support-for-non-standard-baudrates.patch"

# Make stty's priority higher than coreutils
ALTERNATIVE_PRIORITY:rcar-gateway[stty] = "110"

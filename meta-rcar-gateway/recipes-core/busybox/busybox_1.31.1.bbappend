FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-stty-Add-support-for-non-standard-baudrates.patch"

# Make stty's priority higher than coreutils
ALTERNATIVE_PRIORITY[stty] = "110"

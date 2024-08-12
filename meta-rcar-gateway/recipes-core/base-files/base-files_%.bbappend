FILESEXTRAPATHS:prepend:rcar-gateway := "${THISDIR}/${PN}/:"

SRC_URI:append:rcar-gateway = " \
    file://fstab \
"

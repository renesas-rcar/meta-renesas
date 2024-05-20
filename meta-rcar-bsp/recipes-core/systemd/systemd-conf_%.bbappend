FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar = " \
    file://wired.network \
"

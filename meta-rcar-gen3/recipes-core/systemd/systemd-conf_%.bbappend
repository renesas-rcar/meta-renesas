FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen3 = " \
    file://wired.network \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/directfb:"
SRC_URI += "file://dfb_uiomux_support.patch"

DEPENDS += "libuiomux"

PRINC := "${@int(PRINC) + 1}"

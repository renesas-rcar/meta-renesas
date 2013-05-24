DEFAULT_PREFERENCE=""
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

SRC_URI += "file://0001-Add-r8a7790-support.patch"

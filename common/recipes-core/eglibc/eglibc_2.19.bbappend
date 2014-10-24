INSANE_SKIP_nativesdk-nscd  += "textrel"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Remove-armv7-memcpy-optimization.patch"

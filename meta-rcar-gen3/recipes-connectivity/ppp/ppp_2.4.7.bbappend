FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://ppp-fix-building-with-linux-4.8.patch \
    file://0001-ppp-Remove-unneeded-include.patch \
"

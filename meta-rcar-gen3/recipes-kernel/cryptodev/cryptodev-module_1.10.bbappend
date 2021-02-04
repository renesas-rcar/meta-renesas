FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_rcar-gen3 = " \
    file://0001-Fix-build-for-Linux-5.8-rc1.patch \
    file://0001-Fix-build-for-Linux-5.9-rc1.patch \
"

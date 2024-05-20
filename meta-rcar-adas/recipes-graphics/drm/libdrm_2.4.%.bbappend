FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0001-Add-sync_fence_info-and-sync_pt_info.patch \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen3 = " \
    file://0001-Add-sync_fence_info-and-sync_pt_info.patch \
"

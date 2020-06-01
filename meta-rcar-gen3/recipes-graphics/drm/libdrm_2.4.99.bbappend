FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rcar-gen3 = " \
    file://0001-Add-sync_fence_info-and-sync_pt_info.patch \
"

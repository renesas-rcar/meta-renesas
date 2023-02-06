FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0001-Add-sync_fence_info-and-sync_pt_info.patch \
    file://Add-libkms.patch \
"

PACKAGES =+ "${PN}-kms"

PACKAGECONFIG:append = " libkms"
PACKAGECONFIG[libkms] = "-Dlibkms=enabled,-Dlibkms=disabled"

FILES:${PN}-kms = "${libdir}/libkms*.so.*"

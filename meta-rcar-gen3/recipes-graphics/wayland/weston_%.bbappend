require weston.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-Revert-compositor-drm-Enable-planes-for-atomic.patch \
"

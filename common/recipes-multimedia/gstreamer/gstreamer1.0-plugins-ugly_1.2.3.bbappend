PACKAGECONFIG ??= " \
    a52dec lame mad mpeg2dec \
    "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-asfdemux-asfpacket-specify-the-offset-by-an-amount-o.patch \
            file://0002-asfdemux-asfpacket-set-frame-start-code-to-VC-1-adva.patch"

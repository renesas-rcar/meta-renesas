PACKAGECONFIG ??= " orc "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://libav_e500mc.patch \
    "

INSANE_SKIP_${PN} += "textrel"

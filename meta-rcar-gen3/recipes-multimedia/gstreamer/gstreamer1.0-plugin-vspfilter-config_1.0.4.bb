SUMMARY = "GStreamer VSP filter plugin runtime configuration"
SECTION = "multimedia"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

VSPFILTER_CONF:r8a7795 = "gstvspfilter-${MACHINE}_r8a7795.conf"
VSPFILTER_CONF:r8a7796 = "gstvspfilter-${MACHINE}_r8a7796.conf"
VSPFILTER_CONF:r8a77965 = "gstvspfilter-${MACHINE}_r8a77965.conf"
VSPFILTER_CONF:r8a77990 = "gstvspfilter-${MACHINE}_r8a77990.conf"

SRC_URI = " file://${VSPFILTER_CONF} "

do_configure[noexec] = "1"

do_compile[noexec] = "1"

do_install() {
    install -Dm 644 ${WORKDIR}/${VSPFILTER_CONF} ${D}/${sysconfdir}/gstvspfilter.conf
}

FILES:${PN} = " ${sysconfdir}/gstvspfilter.conf "


inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_rcar-gen3 = " file://weston.service"

do_install_append() {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${systemd_unitdir}/system/
		install -m 0644 ${WORKDIR}/weston.service ${D}${systemd_unitdir}/system/
	fi
}

SYSTEMD_SERVICE_${PN} = "weston.service"

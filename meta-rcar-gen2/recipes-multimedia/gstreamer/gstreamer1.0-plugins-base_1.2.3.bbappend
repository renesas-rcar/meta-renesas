require ../../include/gles-control.inc

SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-plugins-base.git;protocol=git;branch=RCAR-GEN2/1.2.3"
SRCREV_rcar-gen2 = "6f90f0cd0435f719d8510b4e255444396117e76d"
S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

# For Common
FILESEXTRAPATHS_prepend_rcar-gen2 := '${THISDIR}/${PN}:'
VSPFILTER_CONFIGS = " \
    file://gstvspfilter-alt.conf \
    file://gstvspfilter-gose.conf \
    file://gstvspfilter-koelsch.conf \
    file://gstvspfilter-lager.conf \
"

# For wayland
PACKAGECONFIG_remove_rcar-gen2 = "${@base_contains("DISTRO_FEATURES", "wayland", "orc", "", d)}"

SRC_URI_append_rcar-gen2 = \
    "${@'${VSPFILTER_CONFIGS}' \
        if '${USE_WAYLAND}' == '1' else ''}"

EXTRA_OECONF_append_rcar-gen2 = " \
    ${@'--enable-vspfilter' \
        if '${USE_WAYLAND}' == '1' else ''}"

do_install_append_rcar-gen2() {
    if [ '${USE_WAYLAND}' = '1' ] ; then
        mkdir ${D}/etc/
        install -m644 ${WORKDIR}/gstvspfilter-${MACHINE}.conf ${D}/etc/gstvspfilter.conf
    fi
}

FILES_${PN}_append_rcar-gen2 = " \
    ${@'${datadir}/gst-plugins-base/1.0/* ${sysconfdir}/*.conf' \
        if '${USE_WAYLAND}' == '1' else ''}"

# For x11
# None

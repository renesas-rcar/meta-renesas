require include/rcar-gen3-path-common.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_append_rcar-gen3 = " \
    file://fstab \
"

do_install_append_rcar-gen3 () {
    echo "export LD_LIBRARY_PATH=\"\${LD_LIBRARY_PATH}:${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

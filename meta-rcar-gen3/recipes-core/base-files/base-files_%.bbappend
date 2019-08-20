require include/rcar-gen3-path-common.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_append = " \
    file://fstab \
"

do_install_append () {
    echo "export LD_LIBRARY_PATH=\"\${LD_LIBRARY_PATH}:${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

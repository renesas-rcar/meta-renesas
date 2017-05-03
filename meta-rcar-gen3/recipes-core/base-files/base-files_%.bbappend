require include/rcar-gen3-path-common.inc

do_install_append () {
    echo "export LD_LIBRARY_PATH=\"${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

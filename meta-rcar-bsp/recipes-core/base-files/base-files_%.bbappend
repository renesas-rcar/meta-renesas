require include/rcar-bsp-path-common.inc

do_install_append_rcar-gen3 () {
    echo "export LD_LIBRARY_PATH=\"\${LD_LIBRARY_PATH}:${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

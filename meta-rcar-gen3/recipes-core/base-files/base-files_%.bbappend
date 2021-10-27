require include/rcar-gen3-path-common.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

SRC_URI:append:rcar-gen3 = " \
    file://fstab \
"

do_install:append:rcar-gen3 () {
    echo "export LD_LIBRARY_PATH=\"\${LD_LIBRARY_PATH}:${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

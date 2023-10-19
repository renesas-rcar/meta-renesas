SUMMARY = "PCIe test aplications"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://ep_setup.sh;beginline=2;endline=2;md5=daad6f7f7a0a286391cd7773ccf79340"

S = "${WORKDIR}/pcie_test"

SRC_URI = " \
    file://pcie_test.tar.gz \
"

do_install() {
    install -d ${D}${ROOT_HOME}
    install -m 755 ${S}/ep_setup.sh ${D}${ROOT_HOME}
    install -m 755 ${S}/pcitest_script.sh ${D}${ROOT_HOME}
    install -m 755 ${S}/pcitest ${D}${ROOT_HOME}
}

FILES:${PN} = "${ROOT_HOME}/*"


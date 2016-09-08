SUMMARY = "OV10640/OV490 SPI flash firmware update tool"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb766567485731157b3c21013c3ce4b8"

S = "${WORKDIR}/v4l2-fw"

SRC_URI = " \
    file://v4l2-fw.tar.gz \
"

do_compile() {
    cd ${S}
    make all || die
}

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/v4l2-fw ${D}${bindir}

    install -d ${D}/usr/share/factory/
    install -m 644 ${S}/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_1280x1080@30_96Mhz.bin ${D}/usr/share/factory/
    install -m 644 ${S}/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_1280x800@30_96Mhz.bin ${D}/usr/share/factory/
    install -m 644 ${S}/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_1280x966@30_96Mhz.bin ${D}/usr/share/factory/
    install -m 644 ${S}/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_640x480@30_96Mhz.bin ${D}/usr/share/factory/
    install -m 644 ${S}/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_640x480@60_96MHz.bin ${D}/usr/share/factory/
    install -m 755 ${S}/ov10640_ov490_flash_0-3.sh ${D}/usr/share/factory/
    install -m 755 ${S}/ov10640_ov490_flash_4-7.sh ${D}/usr/share/factory/
}

FILES_${PN} = " \
    ${bindir}/v4l2-fw \
    /usr/share/factory/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_1280x1080@30_96Mhz.bin \
    /usr/share/factory/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_1280x800@30_96Mhz.bin \
    /usr/share/factory/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_1280x966@30_96Mhz.bin \
    /usr/share/factory/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_640x480@30_96Mhz.bin \
    /usr/share/factory/OV10640_OV490_combine_general_v491_20160105_common_withSPIHeader_640x480@60_96MHz.bin \
    /usr/share/factory/ov10640_ov490_flash_0-3.sh \
    /usr/share/factory/ov10640_ov490_flash_4-7.sh \
"

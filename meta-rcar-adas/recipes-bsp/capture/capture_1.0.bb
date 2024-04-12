SUMMARY = "Camera application test"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9504a7b7666faec5abd046d28a69450e"

DEPENDS_rcar-v4x = "libdrm kernel-module-cmemdrv"

inherit pkgconfig

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/renesas-rcar/capture.git;protocol=https;nobranch=1"

SRCREV_rcar-v3x = "361b1bb9fa0b009df72a3be1dbdfebf47757095e"
SRCREV_rcar-v4x = "369fa415246d6514deb639fbc14cb69e7fa08253"

do_compile() {
    cd ${S}
    make all || die
}

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/capture ${D}${bindir}

    install -d ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_0.sh   ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_4.sh   ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_0-3.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_4-7.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_2cameras_on_display1920x1080.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_4cameras_on_display1920x1080.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_8cameras_on_display1920x1080.sh ${D}/usr/share/tests/
}

FILES_${PN} = " \
    ${bindir}/capture \
    /usr/share/tests/test_lvds_camera_0.sh \
    /usr/share/tests/test_lvds_camera_4.sh \
    /usr/share/tests/test_lvds_camera_0-3.sh \
    /usr/share/tests/test_lvds_camera_4-7.sh \
    /usr/share/tests/test_lvds_2cameras_on_display1920x1080.sh \
    /usr/share/tests/test_lvds_4cameras_on_display1920x1080.sh \
    /usr/share/tests/test_lvds_8cameras_on_display1920x1080.sh \
"


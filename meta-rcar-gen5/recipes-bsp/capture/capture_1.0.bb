SUMMARY = "Camera application test"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=9504a7b7666faec5abd046d28a69450e"

DEPENDS:rcar-gen5 = "libdrm kernel-module-cmemdrv opencv"

inherit pkgconfig


SRC_URI = "git://github.com/renesas-rcar/capture.git;protocol=https;nobranch=1"

SRCREV:rcar-gen5-vpf = "1271dfe4c4d1acb2a13b3bd1461aaf2ff8c2bf6f"
SRCREV:rcar-gen5-evb = "5a4b3269958df0bedc55c5a885df4243c2c18f3b"

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

FILES:${PN} = " \
    ${bindir}/capture \
    /usr/share/tests/test_lvds_camera_0.sh \
    /usr/share/tests/test_lvds_camera_4.sh \
    /usr/share/tests/test_lvds_camera_0-3.sh \
    /usr/share/tests/test_lvds_camera_4-7.sh \
    /usr/share/tests/test_lvds_2cameras_on_display1920x1080.sh \
    /usr/share/tests/test_lvds_4cameras_on_display1920x1080.sh \
    /usr/share/tests/test_lvds_8cameras_on_display1920x1080.sh \
"

INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "buildpaths"
INSANE_SKIP:${PN}-dbg += "buildpaths"

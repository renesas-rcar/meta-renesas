SUMMARY = "Renesas package group for Weston"
LICENSE = "CLOSED & MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup
require include/gles-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"

PACKAGES = " \
    packagegroup-wayland-community \
    packagegroup-graphics-renesas-proprietary \
    packagegroup-graphics-renesas-wayland \
"

PR = "r0"

RDEPENDS:packagegroup-wayland-community = " \
    wayland \
    weston \
    weston-examples \
    alsa-utils \
    alsa-tools \
    libdrm-tests \
    libdrm-kms \
"

RDEPENDS:packagegroup-graphics-renesas-proprietary = " \
    ${@bb.utils.contains('USE_GLES', '1', \
    'kernel-module-gles gles-user-module' , \
    '', d)} \
"

DEPENDS_packagegroup-graphics-renesas-wayland = "libegl"

RDEPENDS:packagegroup-graphics-renesas-wayland = " \
    ${@bb.utils.contains('USE_GLES_WAYLAND', '1', \
    'libgbm libgbm-dev wayland-kms wayland-wsegl', \
    '', d)} \
"

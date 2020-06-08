SUMMARY = "Renesas package group for Weston"
LICENSE = "CLOSED & MIT"

inherit packagegroup
require include/gles-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

PACKAGES = " \
    packagegroup-wayland-community \
    packagegroup-graphics-renesas-proprietary \
"

PR = "r0"

RDEPENDS_packagegroup-wayland-community = " \
    wayland \
    weston \
    weston-examples \
    alsa-utils \
    alsa-tools \
    libdrm-tests \
    libdrm-kms \
"

RDEPENDS_packagegroup-graphics-renesas-proprietary = " \
    ${@bb.utils.contains('USE_GLES', '1', \
    'kernel-module-gles gles-user-module' , \
    '', d)} \
"


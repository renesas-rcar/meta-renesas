SUMMARY = "Multimedia kernel modules package groups"
LICENSE = "GPLv2 & MIT"

DEPENDS = "kernel-module-mmngr kernel-module-mmngrbuf \
    kernel-module-vspm kernel-module-vspmif \
    kernel-module-vsp2driver kernel-module-uvcs \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-kernel-modules \
"

RDEPENDS_packagegroup-multimedia-kernel-modules = " \
    kernel-module-mmngr \
    kernel-module-mmngrbuf \
    kernel-module-vspm \
    kernel-module-vspmif \
    kernel-module-vsp2driver \
    kernel-module-uvcs \
"

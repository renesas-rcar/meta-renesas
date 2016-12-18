SUMMARY = "Multimedia kernel modules package groups"
LICENSE = "GPLv2 & MIT"

require include/gles-control.inc

DEPENDS = "kernel-module-mmngr \
    kernel-module-mmngrbuf \
    kernel-module-vspm \
    kernel-module-vsp2 \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-kernel-modules \
"

RDEPENDS_packagegroup-multimedia-kernel-modules = " \
    kernel-module-mmngrbuf \
    kernel-module-mmngr \
    kernel-module-vspm \
    kernel-module-vsp2 \
"

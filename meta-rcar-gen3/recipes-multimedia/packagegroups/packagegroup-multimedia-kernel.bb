SUMMARY = "Multimedia kernel modules package groups"
LICENSE = "GPLv2 & MIT"

require include/omx-control.inc

DEPENDS = "kernel-module-mmngr kernel-module-mmngrbuf \
    kernel-module-vspm kernel-module-vspmif \
    kernel-module-vsp2driver \
"

DEPENDS += '${@base_conditional("USE_VIDEO_OMX", "1", "kernel-module-uvcs-drv", "", d )}'

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
"

RDEPENDS_packagegroup-multimedia-kernel-modules += " \
    ${@base_conditional("USE_VIDEO_OMX", "1", "kernel-module-uvcs-drv", "", d )} \
"

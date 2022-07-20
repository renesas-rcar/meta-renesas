SUMMARY = "Multimedia kernel modules package groups"
LICENSE = "GPL-2.0-only & MIT"

require include/omx-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

DEPENDS = "kernel-module-mmngr kernel-module-mmngrbuf \
    kernel-module-vspm kernel-module-vspm-if \
    kernel-module-vsp2driver \
"

DEPENDS += '${@oe.utils.conditional("USE_VIDEO_OMX", "1", "kernel-module-uvcs-drv", "", d )}'

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-kernel-modules \
"

RDEPENDS:packagegroup-multimedia-kernel-modules = " \
    kernel-module-mmngr \
    kernel-module-mmngrbuf \
    kernel-module-vspm \
    kernel-module-vspm-if \
    kernel-module-vsp2driver \
"

RDEPENDS:packagegroup-multimedia-kernel-modules += " \
    ${@oe.utils.conditional("USE_VIDEO_OMX", "1", "kernel-module-uvcs-drv", "", d )} \
"

SUMMARY = "Multimedia user libraries modules package groups"
LICENSE = "MIT"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "mmngr-user-module mmngrbuf-user-module \
    vspmif-user-module libalacdla-l libflacdla-l \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-libs \
"

RDEPENDS:packagegroup-multimedia-libs = " \
    mmngr-user-module mmngrbuf-user-module \
    vspmif-user-module \
    libalacdla-l libflacdla-l \
"

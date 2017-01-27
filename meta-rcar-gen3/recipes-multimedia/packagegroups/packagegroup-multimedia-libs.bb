SUMMARY = "Multimedia user libraries modules package groups"
LICENSE = "MIT"

DEPENDS = "mmngr-user-module mmngrbuf-user-module \
    vspmif-user-module libalacdla-l libflacdla-l \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-libs \
"

RDEPENDS_packagegroup-multimedia-libs = " \
    mmngr-user-module mmngrbuf-user-module \
    vspmif-user-module \
    libalacdla-l libflacdla-l \
"

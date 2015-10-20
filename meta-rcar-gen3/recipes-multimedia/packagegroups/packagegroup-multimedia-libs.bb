SUMMARY = "Multimedia user libraries modules package groups"
LICENSE = "MIT"

DEPENDS = "mmngr-user-module mmngrbuf-user-module \
    vspmif-user-module \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-libs \
"

RDEPENDS_packagegroup-multimedia-libs = " \
    mmngr-user-module mmngrbuf-user-module \
    vspmif-user-module \
"

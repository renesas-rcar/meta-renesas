SUMMARY = "Multimedia user libraries modules package groups"
LICENSE = "MIT"

DEPENDS = "\
    mmngr-user-module \
    mmngrbuf-user-module \
    vspm-user-module \
    libmediactl-v4l2 \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-multimedia-libs \
"

RDEPENDS_packagegroup-multimedia-libs = " \
    mmngr-user-module \
    mmngrbuf-user-module \
    vspm-user-module \
    libmediactl-v4l2 \
"


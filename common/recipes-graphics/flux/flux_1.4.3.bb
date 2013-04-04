SUMMARY = "flux is an interface description language used by DirectFB"
HOMEPAGE = "http://directfb.org"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r0"

SRC_URI = "http://directfb.org/downloads/Core/flux/flux-1.4.3.tar.gz"

SRC_URI[md5sum] = "ede8d8688c33d1c7de9cf85ea75aa691"
SRC_URI[sha256sum] = "d9ead7221e6912fc7e7ef324911558b2d6d53c313ed3f04bb06665fcd86b5281"

inherit autotools pkgconfig

BBCLASSEXTEND = "native"

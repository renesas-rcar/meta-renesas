LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=442d4e9f738ff4d05ae6215ae20caa6c"

require include/iccom-control.inc
inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "iccom"

SRC_URI = "file://libiccom.tar.bz2"

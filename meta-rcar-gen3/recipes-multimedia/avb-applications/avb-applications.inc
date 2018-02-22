require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-applications.git;branch=rcar-gen3"
SRCREV = "3cb8c818f89ae0b3e8aa18e64fe8b068327cd3e7"

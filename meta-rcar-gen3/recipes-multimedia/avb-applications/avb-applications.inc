require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-applications.git;branch=rcar-gen3"
SRCREV = "e959010b9b26b684a53db536a02b2f96f0d177ac"

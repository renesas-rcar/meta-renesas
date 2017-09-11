require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-applications.git;branch=rcar-gen3"
SRCREV = "4c4408e12c0cb7bfecdc73ada7639d4bace9273e"

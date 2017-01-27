require include/avb-control.inc
require include/rcar-gen3-modules-common.inc

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-applications.git;branch=rcar-gen3"
SRCREV = "65ed4abc3d403b61405bde89a755da37390052c9"

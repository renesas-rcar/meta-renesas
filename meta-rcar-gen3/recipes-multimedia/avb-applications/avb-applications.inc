require include/rcar-gen3-modules-common.inc

inherit features_check

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "gitsm://github.com/renesas-rcar/avb-applications.git;branch=rcar-gen3"
SRCREV = "8398f6e66eaf487eaf8e9a2a8d60f222456c06a6"

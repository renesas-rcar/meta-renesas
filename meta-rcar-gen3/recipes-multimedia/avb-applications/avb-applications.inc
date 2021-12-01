require include/rcar-gen3-modules-common.inc

inherit features_check

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "gitsm://github.com/renesas-rcar/avb-applications.git;branch=rcar-gen3;protocol=https"
SRCREV = "73ce24224c57c910a2dd22cd52eec97b8289f898"

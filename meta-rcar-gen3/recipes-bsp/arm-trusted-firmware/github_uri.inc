# For Salvator-X and ULCB
S = "${WORKDIR}/git"

BRANCH = "rcar_gen3"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH}"
SRCREV = "09b9054a8e75f0d48eb412efaf665b3c6ea58a34"

PV = "v1.3+renesas+git${SRCPV}"

require recipes-graphics/drm/libdrm.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "4d8674077c006f68ec4771530891df8231da839d"
SRC_URI = "git://git.ideasonboard.com/renesas/drm.git;protocol=git;branch=live \
	file://installtests.patch \
	file://GNU_SOURCE_definition.patch \
	file://0001-modetest-add-the-possibility-to-select-the-refresh-f.patch \
	"

PV_append = "+git${SRCREV}"
S = "${WORKDIR}/git"
EXTRA_OECONF += "--enable-install-test-programs"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_armadillo800eva = " file://fbdev-uiomux-register.patch"

EXTRA_OECONF += "\
	--with-inputdrivers=linuxinput \
"

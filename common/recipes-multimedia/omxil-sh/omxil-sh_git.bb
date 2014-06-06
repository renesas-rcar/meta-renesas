DESCRIPTION = "A collection of OpenMAX IL components for SH-Mobile, using the \
Bellagio OpenMAX IL project framework."
HOMEPAGE = "https://github.com/dhobsong/omxil-sh"
BUGTRACKER = "https://github.com/dhobsong/omxil-sh"
SECTION = "multimedia"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

PR = "r3"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "af89fcc030e3e5f82ce669fdfea2af105a73cf87"
SRC_URI = "git://github.com/dhobsong/omxil-sh.git \
	file://0001-vpu5-avc-parse-correct-pNal-size-calculation-for-EOS.patch \
	file://0001-Removed-include-path-of-VPUMW-and-fixed-the-path-tha.patch \
"
DEPENDS = "libomxil libuiomux"
S = "${WORKDIR}/git"

# for armadillo800eva
SRCREV_armadillo800eva = "bd43cfba750773cd323fb546f279e76b37c6d713"
SRC_URI_append_armadillo800eva = " file://vcp1-library-change.patch"
CHECK_OMXIL_SH_MW = "${USE_RENESAS_MW_VCP1}" 
CHECK_OMXIL_SH_MW += "${USE_RENESAS_MW_VPU5}"
DEPENDS_append_armadillo800eva = ' ${@base_contains("CHECK_OMXIL_SH_MW", "1", "vcp1", "", d)}'

inherit autotools pkgconfig

VPUMW_PATH = "/usr/"

EXTRA_OECONF = "--with-vpumw-path=${VPUMW_PATH} \
	--enable-tl_conv=kernel \
	--enable-tl_conv_internal \
	--with-vpu5_version=VCP1 \
	--disable-vpu5_encoder \
	--disable-meram \
	--enable-vpc \
"
 
do_configure() {
	autoreconf -vif
	oe_runconf
}

PACKAGES = "\
	${PN} \
	${PN}-dev \
	${PN}-staticdev \
"

# need *.so, these are used by omx.
INSANE_SKIP_${PN} = "dev-so" 
FILES_${PN} += " \
	${libdir}/bellagio/*.so.* \
	${libdir}/bellagio/*.so \
"

FILES_${PN}-dev += " \
	${libdir}/bellagio/*.la \
"

FILES_${PN}-staticdev = " \
	${libdir}/bellagio/*.a \
"

#FILES_${PN}-dbg += " \
#	${libdir}/bellagio/.debug/* \
#"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

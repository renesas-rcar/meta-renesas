require linux-dtb-append.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "armadillo800eva"
KBRANCH_DEFAULT = "ltsi"
KBRANCH = "${KBRANCH_DEFAULT}"
KMACHINE = "armadillo800eva"

LINUX_VERSION = "3.4.25"
SRCREV_machine_armadillo800eva="48d7d215d960da249104e337104f6941250149a5"

SRC_URI += "\
	file://armadillo800eva-non_hardware.cfg \
	file://armadillo800eva-preempt-rt.scc \
	file://armadillo800eva-standard.scc \
	file://armadillo800eva.cfg \
	file://armadillo800eva.scc \
	file://missing_required.cfg \
	file://required_redefinition.txt \
	file://specified_non_hdw.cfg \
	file://user-config.cfg \
	file://user-patches.scc \
	"

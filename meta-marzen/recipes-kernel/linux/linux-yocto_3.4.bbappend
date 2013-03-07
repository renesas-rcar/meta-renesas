FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_marzen = "marzen"
KBRANCH_DEFAULT_marzen = "ltsi"
KBRANCH_marzen = "${KBRANCH_DEFAULT}"
KMACHINE_marzen = "marzen"

LINUX_VERSION = "3.4.25"

SRCREV_machine_marzen ?= "48d7d215d960da249104e337104f6941250149a5"

SRC_URI_append_marzen = " \
	file://defconfig \
	file://marzen-standard.scc \
	file://marzen.scc \
	file://user-patches.scc \
	file://marzen.cfg \
	file://user-config.cfg \
	file://missing_required.cfg \
	file://required_redefinition.txt \
	file://specified_non_hdw.cfg \
	"

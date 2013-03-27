FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION = "3.4.24"

PR=1

COMPATIBLE_MACHINE_rbsr = "rbsr"
KBRANCH_DEFAULT_rbsr = "rbsr"
KBRANCH_rbsr = "${KBRANCH_DEFAULT}"
KMACHINE_rbsr = "rbsr"

SRCREV_machine_rbsr ?= "7146d001a5f95068a3e2da23a8b3d15aeb20087a"

SRC_URI_append_rbsr = " \
	file://defconfig \
	file://rbsr-standard.scc \
	file://rbsr.scc \
	file://user-patches.scc \
	file://rbsr.cfg \
	file://user-config.cfg \
	file://missing_required.cfg \
	file://required_redefinition.txt \
	file://specified_non_hdw.cfg \
	\
	file://0001-ARM-shmobile-add-R8A7778-setup-support.patch	\
	file://0002-ARM-shmobile-add-R8A7778-clock-support.patch	\
	file://0003-ARM-shmobile-add-R8A7778-intc-support.patch	\
	file://0004-ARM-shmobile-add-R-Car-M1-RBSR-platform-support.patch	\
	"

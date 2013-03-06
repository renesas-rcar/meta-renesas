FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION = "3.4.24"

COMPATIBLE_MACHINE_bockw = "bockw"
KBRANCH_DEFAULT_bockw = "bockw"
KBRANCH_bockw = "${KBRANCH_DEFAULT}"
KMACHINE_bockw = "bockw"

SRCREV_machine_bockw ?= "7146d001a5f95068a3e2da23a8b3d15aeb20087a"

SRC_URI_append_bockw = " \
	file://defconfig \
	\
	file://0001-Local-ARM-shmobile-add-gic_iid-macro-for-ICCIAR-inte.patch \
	file://0002-Local-ARM-shmobile-add-R8A7778-clock-support.patch \
	file://0003-Local-ARM-shmobile-add-R8A7778-intc-support.patch \
	file://0004-Local-ARM-shmobile-add-R8A7778-setup-support.patch \
	file://0005-Local-ARM-shmobile-add-R-Car-M1A-Bock-W-platform-sup.patch \
	file://0006-Local-ARM-shmobile-bockw-add-SMSC-Eth-support.patch \
	"

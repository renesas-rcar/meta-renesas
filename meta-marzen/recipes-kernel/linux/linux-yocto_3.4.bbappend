FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_marzen = "marzen"
KBRANCH_DEFAULT_marzen = "ltsi"
KBRANCH_marzen = "${KBRANCH_DEFAULT}"
KMACHINE_marzen = "marzen"
PATCHWORK_PATH = "https://patchwork.kernel.org/patch"

LINUX_VERSION = "3.4.25"

SRCREV_machine_marzen ?= "48d7d215d960da249104e337104f6941250149a5"

SRC_URI_append_marzen = " \
	file://defconfig \
	${PATCHWORK_PATH}/2195231/mbox/;downloadfilename=2195231;name=2195231;apply=yes;striplevel=1 \
	${PATCHWORK_PATH}/2195241/mbox/;downloadfilename=2195241;name=2195241;apply=yes;striplevel=1 \
	${PATCHWORK_PATH}/2195221/mbox/;downloadfilename=2195221;name=2195221;apply=yes;striplevel=1 \
	${PATCHWORK_PATH}/2195211/mbox/;downloadfilename=2195211;name=2195211;apply=yes;striplevel=1 \
	"
SRC_URI[2195231.md5sum] = "ad6bb43ef4e3fba56d8b3ad693bfea6a"
SRC_URI[2195231.sha256sum] = "4493147950b84ed8ce5b388266307461e16a5ca09e43904e28b858b8caf69e56"
SRC_URI[2195241.md5sum] = "839ef03e5fa0f26388f1ba1daaadbd15"
SRC_URI[2195241.sha256sum] = "f2c7fa5b81bc65a364509da5339c30c39ac49365f67b5624d593ac119f98b55d"
SRC_URI[2195221.md5sum] = "09babf6c478fa9c24c7e967c27947627"
SRC_URI[2195221.sha256sum] = "79de82f0abc182b2b93afdb8da954311302c69ce38d372a2b899b5c139abfc10"
SRC_URI[2195211.md5sum] = "b77525e365aa6e620e40202edb0700c3"
SRC_URI[2195211.sha256sum] = "3ab9908d4df8b68830e2fd9dee4f84bda5676f82a654cfca5f6cf2e2066c2455"

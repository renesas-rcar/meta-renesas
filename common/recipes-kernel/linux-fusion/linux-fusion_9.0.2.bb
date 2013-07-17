DESCRIPTION = "The linux-fusion modules"
LICENSE = "GPLv2"
# dummy
LIC_FILES_CHKSUM = "file://README;md5=54be18c46d265cc8ea0aafee3034a26d"

DEPENDS = "virtual/kernel"

inherit module

PV = "9.0.2"
PR = "r0"

S = "${WORKDIR}/git"
SRCREV = "52f7ad63b4f98929fb15c2b73b9d40d39089f83e"
SRC_URI = "git://git.directfb.org/git/directfb/core/linux-fusion.git;protocol=git \
	   file://linux-fusion-modules-replace-KERNELDIR-with-KERNEL_SRC.patch"


# inherit autotools pkgconfig

export INSTALL_MOD_DIR="kernel/linux-fusion-modules"
export KERNEL_SRC="${STAGING_KERNEL_DIR}"

do_install_append() {
	install -d ${D}/usr/include/linux
	install -m 0644 ${S}/linux/include/linux/fusion.h ${D}/usr/include/linux
}

FILES_${PN}-headers = "/usr/include/linux"

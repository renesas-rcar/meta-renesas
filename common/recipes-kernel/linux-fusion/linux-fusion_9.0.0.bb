DESCRIPTION = "The linux-fusion modules"
LICENSE = "GPLv2"
# dummy
LIC_FILES_CHKSUM = "file://README;md5=54be18c46d265cc8ea0aafee3034a26d"

DEPENDS = "virtual/kernel"

inherit module

PV = "9.0.0"
PR = "r0"

SRC_URI = "http://directfb.org/downloads/Core/linux-fusion/linux-fusion-${PV}.tar.gz \
	   file://linux-fusion-modules-replace-KERNELDIR-with-KERNEL_SRC.patch"

SRC_URI[md5sum] = "4199617ed8ba205da52fedfb862e4507"
SRC_URI[sha256sum] = "54b5da51a80d29a3b618d1ee504afd581b8fa1885a717ea925607d3547242f70"

# inherit autotools pkgconfig

export INSTALL_MOD_DIR="kernel/inux-fusion-modules"
export KERNEL_SRC="${STAGING_KERNEL_DIR}"

do_install_append() {
	install -d ${D}/usr/include/linux
	install -m 0644 ${S}/linux/include/linux/fusion.h ${D}/usr/include/linux
}

FILES_${PN}-headers = "/usr/include/linux"

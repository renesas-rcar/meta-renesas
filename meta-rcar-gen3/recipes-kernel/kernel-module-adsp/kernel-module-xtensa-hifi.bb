DESCRIPTION = "ADSP Driver for Linux"
LICENSE = "GPL-2.0-only & MIT"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=55979d94ccbb973fdea772250e0c54d6 \
    file://MIT-COPYING;md5=f932f6ad0feea4f97b6e8316e172070e \
"

inherit module features_check

REQUIRED_DISTRO_FEATURES = "adsp"

FILESEXTRAPATHS:prepend := "${THISDIR}/xtensa-hifi:"

SRC_URI = "file://RTM8RC0000ZNA2DS00JFL3E.tar.gz"

S = "${WORKDIR}/RTM8RC0000ZNA2DS00JFL3E"

# Define the extra config for using module.bbclass to build ADSP driver
EXTRA_OEMAKE:append = " KDIR=${STAGING_KERNEL_DIR}"

# Build ADSP kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

# Enable build target for E3 board
do_configure:prepend:r8a77990(){
    sed -i 's|-DTARGET_BOARD_E3=0|-DTARGET_BOARD_E3=1|g' ${S}/Kbuild
}

do_install:append(){
    # Create install directories
    install -d ${D}/${includedir}
    install -d ${D}/${includedir}/sys/xt-shmem

    # Install shared header files
    install -m 644 ${S}/include/*.h ${D}/${includedir}/
    install -m 644 ${S}/include/sys/xt-shmem/*.h ${D}/${includedir}/sys/xt-shmem/
}

# The ADSP driver need adsp firmware for running
RDEPENDS:${PN}:append = " adsp-fw-module"

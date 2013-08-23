require u-boot.inc

# This is needs to be validated among supported BSP's before we can
# make it default
DEFAULT_PREFERENCE = "-1"

# To build u-boot for your machine, provide the following lines in
# your machine config, replacing the assignments as appropriate for
# your machine.
# UBOOT_MACHINE = "omap3_beagle_config"
# UBOOT_ENTRYPOINT = "0x80008000"
# UBOOT_LOADADDRESS = "0x80008000"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=6bc50ecab884fce80cd3ef3da8852b08"

# This revision corresponds to the tag "v2013.01.01"
# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "9e975577252de261b29f830ec2a4315cbd50cfd7"

PV = "v2013.10.01-rc1+git${SRCPV}"
PR = "r0"

SRC_URI = "git://git.denx.de/u-boot-sh.git;branch=bsp/lager;protocol=git"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

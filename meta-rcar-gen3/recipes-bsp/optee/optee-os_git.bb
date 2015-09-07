DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f0fb2f357d31d6a98213b19f57abf927"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/git"

BRANCH = "master"
SRC_URI = "git://github.com/OP-TEE/optee_os.git;protocol=https;branch=${BRANCH}"
SRCREV = "f5117af25f1cf77d55ef1c641a6d648c0cb143e2"

SRC_URI += " \
    file://0001-OPTEE-July-28-2015-local-release.patch \
"

PV = "0.2.0+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "salvator-x"
PLATFORM = "rcar"

export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the flags as it is a standalone application
LD[unexport] = "1"
LDFLAGS[unexport] = "1"
CC="${TARGET_PREFIX}gcc --sysroot=${PKG_CONFIG_SYSROOT_DIR}"
libdir[unexport] = "1"

do_configure() {
    :
}

do_compile() {
    oe_runmake ARCH=arm32 PLATFORM=${PLATFORM}
}

do_install() {
    :
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/out/arm32-plat-${PLATFORM}/core/tee.elf ${DEPLOYDIR}/tee-${MACHINE}.elf
    install -m 0644 ${S}/out/arm32-plat-${PLATFORM}/core/tee.bin ${DEPLOYDIR}/tee-${MACHINE}.bin
    install -m 0644 ${S}/out/arm32-plat-${PLATFORM}/core/tee.srec ${DEPLOYDIR}/tee-${MACHINE}.srec
}
addtask deploy before do_build after do_compile

DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=69663ab153298557a59c67a60a743e5b \
    file://lib/libpng/LICENSE;md5=06a1b6fde6d93170bb72201c8000bf3d \
    file://${WORKDIR}/git_official/LICENSE;md5=69663ab153298557a59c67a60a743e5b \
    file://${WORKDIR}/git_official/lib/libpng/LICENSE;md5=06a1b6fde6d93170bb72201c8000bf3d \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy pythonnative

PV = "2.2.0+renesas+git${SRCPV}"

BRANCH = "rcar_gen3"
SRCREV_renesas = "1e959b66e877546d83b3ac7fb5d10baa4a034acc"
SRCREV_officialgit = "c0c5d399d81a0669f5c8e3bcb20039d65649a78d"
SRCREV_FORMAT = "renesas_officialgit"

SRC_URI = " \
    git://github.com/renesas-rcar/optee_os.git;branch=${BRANCH};name=renesas \
    git://github.com/OP-TEE/optee_os.git;branch=master;name=officialgit;destsuffix=git_official \
"

COMPATIBLE_MACHINE = "(salvator-x|h3ulcb|m3ulcb)"
PLATFORM = "rcar"

DEPENDS = "python-wand-native python-pycrypto-native"

# Needed so that python-wand can find the installed imagemagick install.
export MAGICK_HOME="${STAGING_DIR_NATIVE}${prefix}"

export CROSS_COMPILE64="${TARGET_PREFIX}"

# Let the Makefile handle setting up the flags as it is a standalone application
LD[unexport] = "1"
LDFLAGS[unexport] = "1"
export CCcore="${CC}"
export LDcore="${LD}"
libdir[unexport] = "1"

S = "${WORKDIR}/git"
EXTRA_OEMAKE = "-e MAKEFLAGS="

do_configure() {
    cp -r ${WORKDIR}/git_official/core/lib/libtomcrypt ${B}/core/lib/.
}

do_compile() {
    oe_runmake PLATFORM=${PLATFORM} CFG_ARM64_core=y
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy TEE OS to deploy folder
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.elf ${DEPLOYDIR}/tee-${MACHINE}.elf
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.bin ${DEPLOYDIR}/tee-${MACHINE}.bin
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.srec ${DEPLOYDIR}/tee-${MACHINE}.srec
}
addtask deploy before do_build after do_compile

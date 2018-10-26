DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=69663ab153298557a59c67a60a743e5b \
    file://${WORKDIR}/git_official/LICENSE;md5=69663ab153298557a59c67a60a743e5b \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy pythonnative

PV = "3.1.0+renesas+git${SRCPV}"

BRANCH = "rcar_gen3"
SRCREV_renesas = "459c612224e123658a2ad29a91a3d186342d24a9"
SRCREV_officialgit = "e77020396508fc086d7a4d6137388b116e4a662f"
SRCREV_FORMAT = "renesas_officialgit"

SRC_URI = " \
    git://github.com/renesas-rcar/optee_os.git;branch=${BRANCH};name=renesas \
    git://github.com/OP-TEE/optee_os.git;branch=master;name=officialgit;destsuffix=git_official \
"

# Patch for Yv3.9.0.1
SRC_URI_append = " \
    file://0001-OPTEE_PROVIDER-188185-Fix-a-contxt-size-allocated-by.patch \
    file://0002-OPTEE_PROVIDER-188122-Fix-to-exclusive-control-for-R.patch \
    file://0001-Update-optee_os-Rev1.0.16-rev2.patch \
    file://0001-plat-rcar-fix-MMU-configuration-of-shared-memory.patch \
"

COMPATIBLE_MACHINE = "(salvator-x|h3ulcb|m3ulcb|ebisu)"
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
    git -C ${WORKDIR}/git_official checkout -B official 3.1.0
    git -C ${WORKDIR}/git_official cherry-pick ${SRCREV_officialgit}
    cp -rn ${WORKDIR}/git_official/core/lib/libtomcrypt ${B}/core/lib/.
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

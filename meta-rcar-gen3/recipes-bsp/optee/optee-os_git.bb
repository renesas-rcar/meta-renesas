DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173 \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy python3native

PV = "3.13.0+renesas+git${SRCPV}"

BRANCH = "rcar_gen3_3.13.0"
SRCREV = "93483d65a40690e8a4b9f5e575d0948226efd821"

SRC_URI = " \
    git://github.com/renesas-rcar/optee_os.git;branch=${BRANCH} \
"

SRC_URI_append = " \
    file://0001-mk-gcc.mk-Change-the-path-to-the-library.patch \
"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"
PLATFORM = "rcar"

DEPENDS = "python3-pycryptodome-native python3-pyelftools-native"

export CROSS_COMPILE64="${TARGET_PREFIX}"

# Let the Makefile handle setting up the flags as it is a standalone application
#LD[unexport] = "1"
LDFLAGS[unexport] = "1"
#export CCcore="${CC}"
#export LDcore="${LD}"
libdir[unexport] = "1"

S = "${WORKDIR}/git"
EXTRA_OEMAKE = "-e MAKEFLAGS="

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
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee-raw.bin ${DEPLOYDIR}/tee-${MACHINE}.bin
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.srec ${DEPLOYDIR}/tee-${MACHINE}.srec
}
addtask deploy before do_build after do_compile

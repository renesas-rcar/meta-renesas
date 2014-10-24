DESCRIPTION = "RGX/SGX unit test module"
LICENSE = "CLOSED"
DEPENDS += "gles-kernel-module gles-user-module"
PN = "gles-test-module"
PR = "r0"
OPENGLES3 ?= "0"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = '${@base_conditional( "OPENGLES3", "1", "file://gles3-sample-unittest.tar.bz2", "file://gles2-sample-unittest.tar.bz2", d )}'
S = '${@base_conditional( "OPENGLES3", "1", "${WORKDIR}/gles3-sample-unittest", "${WORKDIR}/gles2-sample-unittest", d )}'

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_populate_lic[noexec] = "1"

do_compile() {
	cd ${S}
	make PKGROOT=${STAGING_DIR_HOST} LIBDRMROOT=${STAGING_DIR_HOST}${prefix}
}

do_install() {
	# Copy binary into sysroot
	mkdir -p ${D}/usr/local/bin/
	if [ "X${OPENGLES3}" = "X0" ]; then
		cp ${S}/OES2_Texture ${D}/usr/local/bin/
		cp ${S}/FragShaderSample.fsh ${D}/usr/local/bin/
		cp ${S}/VertShaderSample.vsh ${D}/usr/local/bin/
	else
		cp ${S}/OES3_Texture ${D}/usr/local/bin/
		cp ${S}/OES3_FragShaderSample.fsh ${D}/usr/local/bin/
		cp ${S}/OES3_VertShaderSample.vsh ${D}/usr/local/bin/
	fi
}

PACKAGES = "\
  ${PN} \
  "
FILES_${PN} = " \
  /usr/local/bin/* \
"
RPROVIDES_${PN} += "gles-test-module"
INSANE_SKIP_${PN} += "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

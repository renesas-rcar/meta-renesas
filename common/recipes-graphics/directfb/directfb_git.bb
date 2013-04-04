require directfb_git.inc

RV = "1.7-0"
PR = "r0"
S = "${WORKDIR}/git"

DEPENDS += "flux-native sysfsutils libuiomux libdrm linux-fusion"

SRC_URI += "file://fix-compilation-with-zlib.patch \
            file://fixsepbuild.patch \
            file://directfbrc"

SRCREV = "abd81b2c29a11f7ddcb06f7277ee965ab09b470c"

EXTRA_OECONF = "\
  --enable-freetype \
  --enable-zlib \
  --disable-sdl \
  --disable-vnc \
  --disable-x11 \
  --disable-imlib2 \
  --disable-mesa \
  --enable-trace \
  --enable-debug \
  --enable-debug-support \
  --disable-one \
  --enable-multi \
  --enable-multicore \
  --enable-network \
  --enable-voodoo \
  --enable-sawman \
  --enable-fusiondale \
  --enable-fusionsound \
  --disable-unique \
  --with-tests \
  --with-fs-drivers=dummy \
  --with-inputdrivers=linuxinput \
  --without-smooth-scaling \
  --without-vorbis \
  --enable-fbdev \
  --enable-png \
  --disable-x11vdpau \
  --disable-devmem \
  --disable-linotype \
  --enable-drmkms \
  --enable-idirectfbgl-egl \
  --with-gfxdrivers=gp2d \
"

TARGET_CFLAGS += "-I${STAGING_INCDIR}/libdrm"

do_configure_prepend() {
	export GP2D_LIBS="-ldrm"
	export DRMKMS_LIBS="-ldrm -lkms"
}

do_install_append () {
	install -d ${D}/etc
        install -m 0644 ${WORKDIR}/directfbrc ${D}/etc/
}

LEAD_SONAME = "libdirectfb-1.7.so.0"


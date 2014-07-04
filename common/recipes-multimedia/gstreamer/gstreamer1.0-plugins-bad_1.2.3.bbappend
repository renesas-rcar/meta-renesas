PACKAGECONFIG ??= " \
    ${@base_contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'opengl', 'eglgles', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'bluetooth', 'bluez', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)} \
    curl uvch264 neon \
    hls sbc dash bz2 smoothstreaming \
    "

DEPENDS += "directfb faad2 libxml2 libuiomux libshvio"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

TARGET_CFLAGS += "-D_GNU_SOURCE"
PACKAGECONFIG[directfb] = "--enable-directfb,--disable-directfb,directfb"

PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('curl', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('eglgles', '')}"
PACKAGECONFIG += "faad directfb"

EXTRA_OECONF += "--enable-directfb --enable-experimental --disable-nls"

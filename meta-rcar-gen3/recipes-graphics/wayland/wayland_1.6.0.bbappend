FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-build-always-build-wayland-scanner.patch \
"

EXTRA_OECONF_class-native = "--disable-documentation"

EXTRA_OECONF = "--disable-documentation --with-host-scanner"

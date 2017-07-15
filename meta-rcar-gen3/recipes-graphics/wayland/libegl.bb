SUMMARY = "Recipe for libegl"
LICENSE = "CLOSED"

DEPENDS = "gles-user-module \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm wayland-kms', '', d)} \
"

PR = "r0"

RDEPENDS_${PN} = " \
    gles-user-module \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm wayland-kms', '', d)} \
"

PROVIDES = "virtual/egl"
RPROVIDES_${PN} += " \
    libegl \
    libegl1 \
"

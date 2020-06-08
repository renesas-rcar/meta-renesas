SUMMARY = "Recipe for libegl"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"

DEPENDS = "gles-user-module \
"

PR = "r0"

RDEPENDS_${PN} = " \
    gles-user-module \
"

PROVIDES = "virtual/egl"
RPROVIDES_${PN} += " \
    libegl \
    libegl1 \
"

require include/gles-control.inc

DEPENDS_append_rcar-gen2 = " \
    ${@'virtual/egl wayland-egl' if '${USE_GLES_WAYLAND}' == '1'  else ''} \
"

DEPENDS_append_rcar-gen2 = "${@bb.utils.contains("DISTRO_FEATURES", "x11", " libxdamage", "", d)}"

RDEPENDS_libcogl-pango_append_rcar-gen3 = " \
	${@bb.utils.contains("DISTRO_FEATURES", "x11", " libxdamage", "", d)}		\
	gdk-pixbuf \
"

RDEPENDS_libcogl-path_append_rcar-gen3 =  " \
	${@bb.utils.contains("DISTRO_FEATURES", "x11", " libxdamage", "", d)}		\
	gdk-pixbuf \
"

EXTRA_OECONF = "--enable-gles2=yes --enable-gl=no ${@bb.utils.contains("DISTRO_FEATURES", "wayland", " --enable-wayland-egl-platform=yes", "", d)}"

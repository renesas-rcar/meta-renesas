require include/rcar-gen3-gles-control.inc
DEPENDS_append_rcar-gen3 = " \
    ${@'libegl' if '${USE_GLES_WAYLAND}' == '1'  else ''}"

DEPENDS_append_rcar-gen3 = " libxdamage"
RDEPENDS_libcogl-pango_append_rcar-gen3 = " libxdamage  gdk-pixbuf"
RDEPENDS_libcogl-path_append_rcar-gen3 = " libxdamage  gdk-pixbuf"

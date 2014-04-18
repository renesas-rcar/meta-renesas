EXTRA_OECONF += "ac_cv_func_malloc_0_nonnull=yes \
	ac_cv_func_realloc_0_nonnull=yes \
"

WAYLAND_ENABLE ?= "0"

FILES_${PN}_remove = '${@base_conditional("WAYLAND_ENABLE", "1", " \
						${libdir}/../include/*h \
						", "", d)}'

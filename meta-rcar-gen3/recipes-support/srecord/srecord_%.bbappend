cmake_do_install:append() {
	DESTDIR='${D}${base_prefix}' cmake_runcmake_build --target ${OECMAKE_TARGET_INSTALL}
}

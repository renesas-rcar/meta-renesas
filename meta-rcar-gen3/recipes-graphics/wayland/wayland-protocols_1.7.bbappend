# In YP2.1.2, /sysroot/usr/share/pkgconfig is not included in PKG_CONFIG_PATH.
# An Installation directory is moved to /usr/lib from /usr/share
# In latest master branch, this problem was fixed by e0bcf333.
do_install_append () {
    install -d ${D}/${libdir}/pkgconfig
    install -m 0644 ${D}/${datadir}/pkgconfig/wayland-protocols.pc ${D}/${libdir}/pkgconfig/
    rm -rf ${D}/${datadir}/pkgconfig
}

FILES_${PN} += "${libdir}/pkgconfig/wayland-protocols.pc"

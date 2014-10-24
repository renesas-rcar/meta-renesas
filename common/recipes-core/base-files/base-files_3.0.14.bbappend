do_install_append () {
	if [ "X${MULTIMEDIA_ENABLE}" = "X1" ] ; then
		echo "export LD_LIBRARY_PATH=\"/usr/local/lib\"" >> ${D}${sysconfdir}/profile
	fi
}

do_install_append () {
	BINVER=`${TARGET_PREFIX}gcc -dumpversion`
	if [ ${BINVER} != ${BINV} ] ; then
		rm -rf ${D}${libdir}/${TARGET_SYS}/${BINVER}/include
		SRC=${D}${libdir}/${TARGET_SYS}/${BINV}
		DEST=${D}${libdir}/${TARGET_SYS}/${BINVER}
		install -d ${SRC} ${DEST}
		for f in ${SRC}/*
		do
			cp ${f} ${DEST}
		done
	fi
}

python do_package_prepend () {
    import subprocess
    subfolder = "*crt* 64 32 x32 n32 libgcc*"
    cc = d.getVar('TARGET_PREFIX', True) + 'gcc'
    cmd = subprocess.Popen([cc, "-dumpversion"], stdout=subprocess.PIPE)
    out, err = cmd.communicate()
    version = out.split()[-1]
    binv = d.getVar('BINV', True)
    if (version != binv):
        pn = d.getVar('PN', True)
        libdir = d.getVar('libdir', True)
        targetsys = d.getVar('TARGET_SYS', True)
        appendprefix = ' ' + libdir + '/' + targetsys + '/' + version + '/'
        appends = ''
        for i in subfolder.split():
            appends = appends + appendprefix + i
        d.appendVar("FILES_" + pn + "-dev", appends)
        if bb.data.inherits_class('nativesdk', d):
            packages_libgcov = 'FILES_nativesdk-libgcov-dev'
        else:
            packages_libgcov = 'FILES_libgcov-dev'
        d.appendVar(packages_libgcov, appendprefix + "libgcov.a")
}


GNUEABI_SUFFIX = ""
python () {
    tarch = d.getVar("TARGET_ARCH", True)

    if tarch == "arm" or tarch == "armeb":
        d.setVar("GNUEABI_SUFFIX", "-gnueabi")
}

cross_canadian_bindirlinks () {
	for i in linux ${CANADIANEXTRAOS}
	do
		i="$i${GNUEABI_SUFFIX}"
		for v in ${CANADIANEXTRAVENDOR}
		do
			d=${D}${bindir}/../${TARGET_ARCH}$v-$i
			if [ -d $d ];
			then
			    continue
			fi
			install -d $d
			for j in `ls ${D}${bindir}`
			do
				p=${TARGET_ARCH}$v-$i-`echo $j | sed -e s,${TARGET_PREFIX},,`
				ln -s ../${TARGET_SYS}/$j $d/$p
			done
		done
       done
}

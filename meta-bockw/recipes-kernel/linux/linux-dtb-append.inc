do_install_append() {
        if test -n "${KERNEL_DEVICETREE}"; then
                for DTS_FILE in ${KERNEL_DEVICETREE}; do
                        if [ ! -f ${DTS_FILE} ]; then
                                echo "Warning: ${DTS_FILE} is not available!"
                                continue
                        fi
                        DTS_BASE_NAME=`basename ${DTS_FILE} | awk -F "." '{print $1}'`
			if [ ! -f ${DTS_BASE_NAME} ]; then
                                echo "Warning: ${DTS_BASE_NAME} is not available!"
                                continue
			fi
                        if [ -f arch/${ARCH}/boot/.uImage.cmd ] ; then
                                cat arch/${ARCH}/boot/zImage ${DTS_BASE_NAME} > arch/${ARCH}/boot/zImage+dtb
                                $(cut -f 3- -d ' ' < arch/${ARCH}/boot/.uImage.cmd | sed -e 's/zImage/zImage+dtb/g' -e 's/uImage/uImage+dtb/g')
                        fi
                done
        fi
}

do_deploy_append() {
	if [ -f ${B}/arch/${ARCH}/boot/uImage+dtb ]; then
		UIMAGE_AND_DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/uImage/uImage+dtb/g"`

		install -d ${DEPLOYDIR}
		install -m 0644 ${B}/arch/${ARCH}/boot/uImage+dtb ${DEPLOYDIR}/${UIMAGE_AND_DTB_NAME}
		cd ${DEPLOYDIR}
		ln -sf ${UIMAGE_AND_DTB_NAME} uImage+dtb
		cd -
	else
		echo "Warning: arch/${ARCH}/boot/uImage+dtb is not available!"
        fi
}

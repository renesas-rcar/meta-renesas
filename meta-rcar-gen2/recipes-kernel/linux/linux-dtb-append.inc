do_install_append() {
    if [ ! -n "${KERNEL_DEVICETREE}" ]; then
        echo "Device tree does not support"
        exit
    fi

    for DTS_FILE in ${KERNEL_DEVICETREE}; do
        if [ ! -f ${DTS_FILE} ]; then
            echo "Warning: ${DTS_FILE} is not available!"
            continue
        fi
        DTS_BASE_NAME=`basename ${DTS_FILE} | awk -F "." '{print $1}'`
        if [ ! -f arch/${ARCH}/boot/dts/${DTS_BASE_NAME}.dtb ]; then
            echo "Warning: ${DTS_BASE_NAME}.dtb is not available!"
            continue
        fi
        # create zImage (for bootz)
        cat arch/${ARCH}/boot/zImage arch/${ARCH}/boot/dts/${DTS_BASE_NAME}.dtb > arch/${ARCH}/boot/zImage+dtb.${DTS_BASE_NAME}
        # create uImage
        $(cut -f 3- -d ' ' < arch/${ARCH}/boot/.uImage.cmd | sed -e "s/zImage/zImage+dtb.${DTS_BASE_NAME}/g" -e "s/uImage/uImage+dtb.${DTS_BASE_NAME}/g")
    done
}

do_deploy_append() {
    # zImage
    if [ -f ${B}/arch/${ARCH}/boot/zImage -a "${KERNEL_IMAGETYPE}" != "zImage" ]; then
        ZIMAGE_AND_DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/uImage/zImage/g"`

        install -d ${DEPLOYDIR}
        install -m 0644 ${B}/arch/${ARCH}/boot/zImage ${DEPLOYDIR}/${ZIMAGE_AND_DTB_NAME}
        cd ${DEPLOYDIR}
        ln -sf ${ZIMAGE_AND_DTB_NAME} zImage
        cd -
    fi

    # zImage + DTB
    install -d ${DEPLOYDIR}
    for DTS_FILE in ${KERNEL_DEVICETREE}; do
	DTS_BASE_NAME=`basename ${DTS_FILE} | awk -F "." '{print $1}'`
    	if [ -f ${B}/arch/${ARCH}/boot/zImage+dtb.${DTS_BASE_NAME} ]; then
        	ZIMAGE_AND_DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/uImage/zImage+dtb.${DTS_BASE_NAME}/g"`

        	install -m 0644 ${B}/arch/${ARCH}/boot/zImage+dtb.${DTS_BASE_NAME} ${DEPLOYDIR}/${ZIMAGE_AND_DTB_NAME}
        	cd ${DEPLOYDIR}
        	ln -sf ${ZIMAGE_AND_DTB_NAME} zImage+dtb.${DTS_BASE_NAME}
        	cd -
        else
        	echo "Warning: arch/${ARCH}/boot/zImage+dtb.${DTS_BASE_NAME} is not available!"
        fi
    done

    # uImage (zImage + DTB)
    for DTS_FILE in ${KERNEL_DEVICETREE}; do
	DTS_BASE_NAME=`basename ${DTS_FILE} | awk -F "." '{print $1}'`
    	if [ -f ${B}/arch/${ARCH}/boot/uImage+dtb.${DTS_BASE_NAME} ]; then
        	UIMAGE_AND_DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/uImage/uImage+dtb.${DTS_BASE_NAME}/g"`

        	install -m 0644 ${B}/arch/${ARCH}/boot/uImage+dtb.${DTS_BASE_NAME} ${DEPLOYDIR}/${UIMAGE_AND_DTB_NAME}
        	cd ${DEPLOYDIR}
        	ln -sf ${UIMAGE_AND_DTB_NAME} uImage+dtb.${DTS_BASE_NAME}
        	cd -
    	else
        	echo "Warning: arch/${ARCH}/boot/uImage+dtb.${DTS_BASE_NAME} is not available!"
    	fi
    done
}

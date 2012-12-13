do_devicetree_zimage() {
    if test -n "${KERNEL_DEVICETREE}" ; then
        for DTS_FILE in ${KERNEL_DEVICETREE}; do
            DTS_BASE_NAME=`basename ${DTS_FILE} | awk -F "." '{print $1}'`
            DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
            DTB_SYMLINK_NAME=`echo ${KERNEL_IMAGE_SYMLINK_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
            ls -l 
            echo "--------"
            echo "DTS_BASE_NAME" ${DTS_BASE_NAME}
            echo "KERNEL_IMAGE_BASE_NAME " ${KERNEL_IMAGE_BASE_NAME}
            echo "DEPLOY_DIR_IMAGE" ${DEPLOY_DIR_IMAGE}
            echo "--------"
	    cat arch/arm/boot/zImage ${DTS_BASE_NAME} > ${KERNEL_IMAGE_BASE_NAME}+dtb
            rm -f ${KERNEL_IMAGE_SYMLINK_NAME}+dtb
            ln -sf ${KERNEL_IMAGE_BASE_NAME}+dtb ${KERNEL_IMAGE_SYMLINK_NAME}+dtb
        done
    fi
}

addtask devicetree_zimage after do_install before do_package do_deploy


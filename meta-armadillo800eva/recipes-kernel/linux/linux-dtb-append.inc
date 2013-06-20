do_deploy_append() {
        if test -n "${KERNEL_DEVICETREE}"; then
                for DTS_FILE in ${KERNEL_DEVICETREE}; do
                        if [ ! -f ${DTS_FILE} ]; then
                                echo "Warning: ${DTS_FILE} is not available!"
                                continue
                        fi
                        cd ${DEPLOYDIR}
                        if [ -f zImage ] ; then
                                cat zImage ${DTB_SYMLINK_NAME}.dtb > ${DTB_NAME}.zImage+dtb
                                ln -sf ${DTB_NAME}.zImage+dtb ${DTB_SYMLINK_NAME}.zImage+dtb
                        fi
                        cd -
                done
        fi
}

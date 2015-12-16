FILES_${PN}-dev = "\
    ${base_libdir}/libgcc*.so \
    ${@base_conditional('BASETARGET_SYS', '${TARGET_SYS}', '', '${libdir}/${BASETARGET_SYS}*', d)} \
    ${libdir}/${TARGET_SYS}/${BINV}* \
"

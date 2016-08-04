# USE_CMSBCM for Basic Color Management Middleware for Linux
USE_CMSBCM = "${@bb.utils.contains('DISTRO_FEATURES', 'cmsbcm', '1', '0', d)}"

# USE_CMSBLC for Backlight Control Middleware for Linux
USE_CMSBLC = "${@bb.utils.contains('DISTRO_FEATURES', 'cmsblc', '1', '0', d)}"

# USE_CMSDGC for Dynamic Gamma Correction Middleware for Linux
USE_CMSDGC = "${@bb.utils.contains('DISTRO_FEATURES', 'cmsdgc', '1', '0', d)}"

# CMS Flag list
LIST_CMS_FLAG = " \
    ${USE_CMSBCM} \
    ${USE_CMSBLC} \
    ${USE_CMSDGC} \
"

# USE_CMS for Color Management Middleware
USE_CMS = "${@'1' if '1' in '${LIST_CMS_FLAG}' else '0'}"

def enable_cms(bcm, blc, dgc):
    if bcm == "1" or blc == "1" or dgc == "1":
        return 1
    return 0

# USE_CMSBCM for Basic Color Management Middleware for Linux
USE_CMSBCM = "${@base_contains('DISTRO_FEATURES', 'cmsbcm', '1', '0', d)}"

# USE_CMSBLC for Backlight Control Middleware for Linux
USE_CMSBLC = "${@base_contains('DISTRO_FEATURES', 'cmsblc', '1', '0', d)}"

# USE_CMSDGC for Dynamic Gamma Correction Middleware for Linux
USE_CMSDGC = "${@base_contains('DISTRO_FEATURES', 'cmsdgc', '1', '0', d)}"

# USE_CMS for Color Management Middleware
USE_CMS = "${@enable_cms(USE_CMSBCM, USE_CMSBLC, USE_CMSDGC)}"

require include/multimedia-control.inc
require include/cms-control.inc
require include/adsp-control.inc
require include/dtv-dvd-control.inc
require include/avb-control.inc
require include/omx-control.inc

# Common Multi Media Packages (MMP)
MULTIMEDIA_PKGS = " \
    packagegroup-multimedia-kernel-modules \
    packagegroup-multimedia-libs \
"

# MMP test Packages
MULTIMEDIA_TP_PKGS = " \
    vspmif-tp-user-module \
"

# OMX MMP Packages
OMX_MULTIMEDIA_PKGS = " \
    omx-user-module \
"

# CMS MMP Packages
CMS_MULTIMEDIA_PKGS = " \
    cms-user-module \
"

# Kernel modules of DTV
DTV_KERNEL_PKGS = " \
    kernel-module-scu-src \
    kernel-module-ssp \
    kernel-module-tddmac \
    kernel-module-tsif \
"

# DTV Packages
DTV_PKGS = " \
    ${DTV_KERNEL_PKGS} \
    dtv-user-module \
"

# DVD Packages
DVD_PKGS = " \
    dvd-user-module \
"

# ADSP Packages
ADSP_PKGS = " \
    kernel-module-xtensa-hifi \
    adsp-if-module \
    adsp-fw-module \
"

# Kernel modules of AVB
AVB_KERNEL_PKGS = " \
    kernel-module-avb-streaming \
    kernel-module-avb-mch \
    kernel-module-avb-mse \
"

# AVB Packages
AVB_PKGS = " \
    ${AVB_KERNEL_PKGS} \
    avbtool \
    linuxptp \
    openavnu-mrpd \
    openavnu-gptp \
    openavnu-maap \
    avb-demoapps-avblauncher \
    avb-demoapps-mrpdummy \
    avb-demoapps-simple \
"

# Renesas MMP 32bit packages
MMP_32BIT_PKGS = " \
    ${@base_conditional("USE_MULTIMEDIA", "1", "lib32-packagegroup-multimedia-libs lib32-omx-user-module", "", d)} \
    ${@base_conditional("USE_MULTIMEDIA_TP", "1", "lib32-vspmif-tp-user-module", "", d)} \
    ${@base_conditional("USE_CMS", "1", "lib32-cms-user-module", "", d)} \
    ${@base_conditional("USE_DTV", "1", "lib32-dtv-user-module", "", d)} \
    ${@base_conditional("USE_DVD", "1", "lib32-dvd-user-module", "", d)} \
    ${@base_conditional("USE_ADSP", "1", "lib32-adsp-if-module", "", d)} \
"

# Install pkg selection
IMAGE_INSTALL_append = " \
    ${@base_conditional("USE_MULTIMEDIA", "1", " ${MULTIMEDIA_PKGS}", "", d)} \
    ${@base_conditional("USE_MULTIMEDIA_TP", "1", " ${MULTIMEDIA_TP_PKGS}", "", d)} \
    ${@base_conditional("USE_OMX_USER_MODULE", "1", " ${OMX_MULTIMEDIA_PKGS}", "", d)} \
    ${@base_conditional("USE_CMS", "1", " ${CMS_MULTIMEDIA_PKGS}", "", d)} \
    ${@base_conditional("USE_DTV", "1", " ${DTV_PKGS}", "", d)} \
    ${@base_conditional("USE_DVD", "1", " ${DVD_PKGS}", "", d)} \
    ${@base_conditional("USE_ADSP", "1", " ${ADSP_PKGS}", "", d)} \
    ${@base_conditional("USE_AVB", "1", " ${AVB_PKGS}", "", d)} \
    ${@base_conditional("USE_32BIT_MMP", "1", " ${MMP_32BIT_PKGS}", "", d)} \
"

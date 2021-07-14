require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc
require include/adsp-control.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v4.14.75-ltsi/rcar-3.9.10"
SRCREV = "93d91993f5d58fd4a9a9559802b12578373cd7e3"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@base_conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
"

# Add ADSP ALSA driver
SUPPORT_ADSP_ASOC = " \
    file://0001-ADSP-add-document-for-compatible-string-renesas-rcar.patch \
    file://0002-ADSP-add-ADSP-sound-driver-source.patch \
    file://0003-ADSP-add-build-for-ADSP-sound-driver.patch \
    file://0004-ADSP-integrate-ADSP-sound-for-H3-M3-M3N-board.patch \
    file://0005-ADSP-integrate-ADSP-sound-for-E3-board.patch \
    file://0006-ADSP-remove-HDMI-support-from-rcar-sound.patch \
"

SRC_URI_append = " \
    ${@base_conditional("USE_ADSP", "1", "${SUPPORT_ADSP_ASOC}", "", d)} \
"

S = "${WORKDIR}/git"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc
require include/iccom-control.inc
require include/adsp-control.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v4.14.75-ltsi/rcar-3.9.9"
SRCREV = "4d2a32e5d314e2c77346f13e8736e97ca3e1b90b"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# Add python3 support to fix Perf build failure
SRC_URI_append = " \
    file://0001-perf-tools-Add-Python-3-support.patch \
"

# Fix patch for Weston 8.0.0 issue
SRC_URI_append = " \
    file://0001-drm-rcar-du-Set-primary-plane-zpos-immutably-at-init.patch \
"

# Enable RPMSG_VIRTIO depend on ICCOM
SRC_URI_append = " \
    ${@oe.utils.conditional("USE_ICCOM", "1", " file://0001-rpmsg-Add-message-to-be-able-to-configure-RPMSG_VIRT.patch", "", d)} \
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
    ${@oe.utils.conditional("USE_ADSP", "1", "${SUPPORT_ADSP_ASOC}", "", d)} \
"

S = "${WORKDIR}/git"

# W/A Fix build issue with Linux v4.14
SRC_URI_append = " \
    file://0001-arm64-bpf-correct-broken-uapi-for-BPF_PROG_TYPE_PERF.patch \
"

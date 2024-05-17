require include/rcar-bsp-path-common.inc
require include/rcar-kernel-info-common.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_append_rcar = " \
    file://fstab \
"

do_install_append_rcar () {
    echo "export LD_LIBRARY_PATH=\"\${LD_LIBRARY_PATH}:${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

inherit module-base

do_install_basefilesissue_append_rcar () {
    # Yocto version and codename
    printf "${DISTRO_NAME} " >> ${D}${sysconfdir}/issue.e2

    distro_version_nodate=${@'${DISTRO_VERSION}'.replace('snapshot-${DATE}','snapshot').replace('${DATE}','')}
    printf "%s " $distro_version_nodate >> ${D}${sysconfdir}/issue.e2

    printf "(${DISTRO_CODENAME})" >> ${D}${sysconfdir}/issue.e2
    echo >> ${D}${sysconfdir}/issue.e2

    # Linux kernel
    printf "Linux kernel: ${KERNEL_VERSION} / ${RENESAS_BSP_BRANCH}" >> ${D}${sysconfdir}/issue.e2
    echo >> ${D}${sysconfdir}/issue.e2

    # YBSP version
    printf "BSP Renesas version: ${BSP_RENESAS_VERSION}" >> ${D}${sysconfdir}/issue.e2
    echo >> ${D}${sysconfdir}/issue.e2
}

# Set hostname for V3H/V3M
hostname_r8a77980 = "v3x"
hostname_r8a77970 = "v3x"
hostname_r8a779g0 = "v4x"
hostname_r8a779h0 = "v4x"
